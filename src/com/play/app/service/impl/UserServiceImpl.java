package com.play.app.service.impl;

import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.crypto.NoSuchPaddingException;
import javax.persistence.Column;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.play.app.dto.UserDTO;
import com.play.app.entity.Person;
import com.play.app.entity.PersonTournament;
import com.play.app.entity.PersonTournamentTemp;
import com.play.app.entity.Tournament;
import com.play.app.entity.User;
import com.play.app.facade.CommonUtils;
import com.play.app.facade.EncryptionUtils;
import com.play.app.facade.SecurityUtils;
import com.play.app.repositories.PersonRepository;
import com.play.app.repositories.PersonTournamentRepository;
import com.play.app.repositories.PersonTournamentRepositoryTemp;
import com.play.app.repositories.TournamentRepository;
import com.play.app.repositories.UserRepository;
import com.play.app.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private PersonRepository personRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PersonTournamentRepositoryTemp personTournamentRepositoryTemp;
	
	@Autowired
	private TournamentRepository tournamentRepository;
	
	@Autowired
	private PersonTournamentRepository personTournamentRepository;

	@Override
	public UserDTO findById(Integer id) {
		return modelMapper.map(userRepository.findOne(id), UserDTO.class);
	}

	@Override
	public UserDTO findByUserId(Integer userId) {
		User user = userRepository.findByUserId(userId);
		if (user != null) {
			return modelMapper.map(user, UserDTO.class);
		}
		return null;
	}
	
	@Override
	public UserDTO findByUserName(String userName) {
		User user = userRepository.findByUserName(userName);
		if (user != null) {
			 UserDTO dto=modelMapper.map(user, UserDTO.class);
			 dto.setPersonId(user.getPerson().getPersonId());
			 dto.setPersonViewTitle(user.getPerson().getTitle());
			 dto.setToken(user.getPerson().getToken());
			 
			  List<PersonTournament> personTournamentlist=personTournamentRepository.findByPersonId(user.getPerson().getPersonId());
				if(personTournamentlist!=null && personTournamentlist.size()>0){
					//TODO multiple tournament at time for a user
					for (PersonTournament personTournamentTemp : personTournamentlist) {
						if(personTournamentTemp.getTournament().getActive()){
							dto.setTournamentId(personTournamentTemp.getTournamentId());
							dto.setTeamId(personTournamentTemp.getTeamId());
						     break;
						}
					}
					
					
				}
			 return dto;
		}
		return null;
	}

	@Override
	@Transactional
	public UserDTO saveUser(UserDTO userDTO) {
		try{
		
		User user = modelMapper.map(userDTO, User.class);
		
		//validate token
		Person p= personRepository.findByToken(userDTO.getToken());
		User u =userRepository.findByUserName(user.getUserName());
		if(u!=null){
			userDTO.setRegistrationStatus(false);
			userDTO.setMessage("Try a different username !");
			return userDTO;
		}
		Tournament t =tournamentRepository.findByTournamentId(userDTO.getTournamentId());
		
		if(p!=null && t!=null){
			
			if(p.getTokenUsed()==false){
				
				user.setForcePasswordChange(false);
				user.setPerson(p);
				user.setCreatedBy(user.getUserName());
				user.setLastModifiedBy(user.getUserName());
				user.setCreatedDate(new Date());
				user.setLastModifiedDate(new Date());
				userRepository.save(user);
				
				PersonTournamentTemp pt=new PersonTournamentTemp();
				pt.setPersonId(p.getPersonId());
				pt.setTournamentId(t.getTournamentId());
				//pt.setTournament(new Tournament(t.getTournamentId()));
				
				pt.setTeamId(userDTO.getTeamId());
				pt.setTotalPoints(new Double(0));
				pt.setRanking(0);
				pt.setGamePlayed(0);
				pt.setGameLeft(60);
				pt.setWin(0);
				pt.setLoss(0);
				pt.setDraw(0);
				pt.setCancelled(0);
				pt.setCreatedBy(user.getUserName());
				pt.setLastModifiedBy(user.getUserName());
				pt.setCreatedDate(new Date());
				pt.setLastModifiedDate(new Date());
				personTournamentRepositoryTemp.save(pt);
				
				p.setTokenUsed(true);
				personRepository.save(p);
			
				userDTO.setRegistrationStatus(true);
				userDTO.setMessage("Registration Successful");
			}
			else{
				userDTO.setRegistrationStatus(false);
				userDTO.setMessage("Invite Information has been utilized!");
			}
		}
			else{
				//if(p!=null && t!=null)  is false
				
			 userDTO.setRegistrationStatus(false);
			 userDTO.setMessage("Invalid Invite or tournament Information !");
			}
		}
		catch(Throwable t){
			t.printStackTrace();
			userDTO.setRegistrationStatus(false);
			userDTO.setMessage("Unable to register !");
		}
		
		return userDTO;
	}

	@Override
	public void deleteUserById(Integer id) {
		userRepository.delete(id);
	}

	@Override
	public List<UserDTO> findAllUsers() {
		List<User> userEntityList = (List<User>) userRepository.findAll();
		Type listType = new TypeToken<List<UserDTO>>() {
		}.getType();
		List<UserDTO> staffDTOList = modelMapper.map(userEntityList, listType);
		return staffDTOList;
	}

	@Override
	public void deleteAllUsers() {
		List<UserDTO> userList = findAllUsers();
		for (UserDTO user : userList) {
			deleteUserById(user.getUserId());
		}
	}

	@Override
	public boolean isUserExist(UserDTO user) {

		if (!StringUtils.isEmpty(user.getUserId())) {
			if (findByUserId(user.getUserId()) != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUserName(username);
		
		if (user != null) {
			if(user.getEnabled()==false){
				
				throw new UsernameNotFoundException("User is disabled");
			}
			String[] userRole = user.getUserRoles().split(",");
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					AuthorityUtils.createAuthorityList(userRole));
		} else {
			throw new UsernameNotFoundException("User not found");
		}
	}



	@Override
	public UserDTO resetPassword(UserDTO userDTO) {
		
		try{
			
			User user =userRepository.findByUserName(userDTO.getUserName());
				
			
			if(user!=null){
				
				if(user.getPerson().getToken().equals(userDTO.getToken())){
					user.setPerson(new Person(user.getPerson().getPersonId()));
					user.setPassword(userDTO.getPassword());
					userRepository.save(user);	
					userDTO.setRegistrationStatus(true);
					userDTO.setMessage("Password updated successfully !");
					return userDTO;
					
				}
				else{
					userDTO.setRegistrationStatus(false);
					userDTO.setMessage("Invalid Invite Information!");
					return userDTO;
				}
			}
			 userDTO.setRegistrationStatus(false);
			 userDTO.setMessage("Invalid User Name Information !");
			}
			catch(Throwable t){
				t.printStackTrace();
				userDTO.setRegistrationStatus(false);
				userDTO.setMessage("Unable to reset password !");
			}
			
			return userDTO;

	}
}
