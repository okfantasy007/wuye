package util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageTool {
	
	private static final int WIDTH=200;
	private static final int HEIGHT=200;
	
	/**
	 * ����ͼƬ�ķ���  ֧����ȷ��jpg��png��bmp,gif(������ʽ)
	 * @param sourceImage ԭʼͼƬ
	 * @param destImage ����֮��ͼƬ
	 * @param formatName �ļ���ʽ������ jpg��png��bmp��gif
	 * @throws Exception
	 */
	public static void getPreviewImage(File sourceImage,File destImage,String formatName)throws Exception{
		//��ȡԭʼͼƬ�ļ�
		BufferedImage source =ImageIO.read(sourceImage);
		//��������֮��ͼƬ������
		BufferedImage newImage  =new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		int x =source.getWidth();
		int y =source.getHeight();
		
		int x1 =WIDTH;
		int y1 =HEIGHT;
		if( (x*HEIGHT)>(WIDTH*y) ){
			y1=y*WIDTH/x;
		}
		else if((x*HEIGHT)<(WIDTH*y)){
			x1=x*HEIGHT/y;
		}
		Graphics2D g2d= newImage.createGraphics();
		g2d.setColor(new Color(255, 255, 255, 255));

		g2d.fillRect (0, 0, WIDTH, HEIGHT);
		Image image =source.getScaledInstance(x1, y1, Image.SCALE_SMOOTH);
		g2d.drawImage(image, (WIDTH-x1)/2, (HEIGHT-y1)/2, null);
		
		if("gif".equalsIgnoreCase(formatName.trim())){
			AnimatedGifEncoder encoder =new AnimatedGifEncoder();
			encoder.start(destImage.getPath());
			encoder.addFrame(newImage);
			encoder.finish();
		}else{
			ImageIO.write(newImage, formatName, destImage);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		getPreviewImage(new File("C:/sss.jpg"), new File("C:/003.jpg"),"jpg");
	}
}
