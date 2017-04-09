package com.play.app.facade;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtils {

	public static BufferedImage getMergedImage(BufferedImage background, BufferedImage image1, BufferedImage image2,
			BufferedImage logo, String text1, String text2) {
		// create the new image, canvas size is the max. of both image sizes
		int w = Math.max(background.getWidth(), background.getWidth());
		int h = Math.max(background.getHeight(), background.getHeight());
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		double ratio = (double) image1.getWidth() / image1.getHeight();
		int width = (int) ((950) / ratio);
		int height = (int) (150 * ratio);

		image1 = getScaledInstance(image1, width, height, "", false);

		// ratio = (double) image2.getWidth() / image2.getHeight();
		// width = (int) ((950)/ratio);
		// height = (int) (150*ratio);

		image2 = getScaledInstance(image2, width, height, "", false);

		// paint both images, preserving the alpha channels
		Graphics g = combined.getGraphics();
		g.drawImage(background, 0, 0, null);
		g.drawImage(image1, 70, 20, null);
		g.drawImage(image2, 70, 330, null);
		g.drawImage(logo, 420, 550, null);

		// g.setFont(new Font("Verdana", 1, 12));

		// g.drawString(text1, 120, 540);
		// g.drawString(text2, 95, 557);
		// Save as new image
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(combined, "PNG", baos);
			InputStream in = new ByteArrayInputStream(baos.toByteArray());
			return ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint,
			boolean higherQuality) {
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = (BufferedImage) img;
		int w, h;
		if (higherQuality) {
			// Use multi-step technique: start with original size, then
			// scale down in multiple passes with drawImage()
			// until the target size is reached
			w = img.getWidth();
			h = img.getHeight();
		} else {
			// Use one-step technique: scale directly from original
			// size to target size with a single drawImage() call
			w = targetWidth;
			h = targetHeight;
		}

		do {
			if (higherQuality && w > targetWidth) {
				w /= 2;
				if (w < targetWidth) {
					w = targetWidth;
				}
			}

			if (higherQuality && h > targetHeight) {
				h /= 2;
				if (h < targetHeight) {
					h = targetHeight;
				}
			}

			BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g2 = tmp.createGraphics();
			// g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
			g2.drawImage(ret, 0, 0, w, h, null);
			g2.dispose();

			ret = tmp;
		} while (w != targetWidth || h != targetHeight);

		return ret;
	}

	public static void main(String[] args) throws IOException {
		String imagePath = "D:\\Temp\\images\\";

		BufferedImage background = ImageIO.read(new File(imagePath, "background.png"));
		BufferedImage image1 = ImageIO.read(new File(imagePath, "before1.png"));
		BufferedImage image2 = ImageIO.read(new File(imagePath, "after1.png"));
		BufferedImage logo = ImageIO.read(new File(imagePath, "logo-small.png"));

		BufferedImage combined = getMergedImage(background, image1, image2, logo, "02 February 2017",
				"Al Reem, Arabian Ranches");
		ImageIO.write(combined, "PNG", new File(imagePath, "wash-completion.png"));
	}
}