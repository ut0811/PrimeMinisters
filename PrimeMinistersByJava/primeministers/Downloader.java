package primeministers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * ダウンローダ：総理大臣のCSVファイル・画像ファイル・サムネイル画像ファイルをダウンロードする。
 * バグ（2013年12月9日）
 */
public class Downloader extends IO
{
	/**
	 * ここを作成してください。
	 * まず、次のページを参照しながら、スケルトン（スタブ）を作ることから始めましょう。
	 * http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/CSV2HTML/PrimeMinistersJavaDoc/index.html
	 */
	
	/**
	 * 総理大臣の情報を記したCSVファイルの在処(URL文字列)を記憶するフィールド。
	 * 良好（2013年12月9日）
	 */
	private String url;
	
	/**
	 * ダウンローダのコンストラクタ。
	 * 良好（2013年12月9日）
	 */
	public Downloader()
	{
		this.url = this.url();
	}
	
	/**
	 * 総理大臣の情報を記しCSVファイルをダウンロードする。
	 * 良好（2013年12月9日）
	 */
	public void downloadCSV()
	{
		ArrayList<String> aCollection = IO.readTextFromURL(this.url);
		File aFile = new File(IO.directoryOfPages(),"PrimeMinisters.csv");
		IO.writeText(aCollection, aFile);
		return;
	}
	
	
	/**
	 * 総理大臣の画像群をダウンロードする。
	 * 良好（2013年12月9日）
	 */
	public void downloadImages()
	{
		ArrayList<BufferedImage> images =this.table.images();
		boolean result = false;	

		File aFile = new File(IO.directoryOfPages(),"images");
		if(aFile.exists() == false)
		{
			aFile.mkdir();
		}


		for(Tuple aTuple :this.table().tuples()){
			try{
				int index = aTuple.attributes().indexOfImage();
				String str = (aTuple.values().get(index));

				System.out.println(this.urlString()+aTuple.values().get(index));
				BufferedImage image = ImageIO.read(new URL(this.urlString()+aTuple.values().get(index))); 
				images.add(image);
			
				try {
					System.out.println("あ:"+str);
					result = ImageIO.write(image, "jpeg", new File(IO.directoryOfPages(),str));
				} catch (Exception e) {
					e.printStackTrace();
					result = false;
				}

			
			}
			catch (Exception anException)
			{
				anException.printStackTrace();
				System.out.println("しっぱい");

			}	

		}
		return;
	}
	
	/**
	 * 総理大臣の画像群またはサムネイル画像群をダウンロードする。
	 * 良好（2013年12月9日）
	 */
	private void downloadPictures(int indexOfPicture)
	{
		/*
		BufferedImage readImage;
		BufferedImage readThumbnail;
		try
		{
			readImage = ImageIO.read(new URL(this.urlString()+"images/0"+Integer.toString(indexOfPicture)+".jpg"));
			readThumbnail = ImageIO.read(new URL(this.urlString()+"thumbnails/0"+Integer.toString(indexOfPicture)+".jpg"));
		}
		catch (Exception anException)
		{
			anException.printStackTrace();
		}
		*/
		return;
	}
	
	/**
	 * 総理大臣の画像群をダウンロードする。
	 * 良好（2013年12月9日）
	 */
	public void downloadThumbnails()
	{
		ArrayList<BufferedImage> thumbnails =this.table.thumbnails();
		boolean result = false;	
		File aFile = new File(IO.directoryOfPages(),"thumbnails");
			if(aFile.exists() == false)
			{
				aFile.mkdir();
			}

		for(Tuple aTuple :this.table().tuples()){
			try{
				int index = aTuple.attributes().indexOfThumbnail();
				String str = (aTuple.values().get(index));

				System.out.println(this.urlString()+aTuple.values().get(index));
				BufferedImage image = ImageIO.read(new URL(this.urlString()+aTuple.values().get(index)));
				thumbnails.add(image);
				
				try {
					System.out.println("あ:"+str);
					result = ImageIO.write(image, "jpeg", new File(IO.directoryOfPages(),str));
				} catch (Exception e) {
					e.printStackTrace();
					result = false;
				}
			
			}
			catch (Exception anException)
			{
				anException.printStackTrace();
				System.out.println("しっぱい");

			}
		}	
		return;
	}
	
	/**
	 * 総理大臣の情報を記したCSVファイルをダウンロードして、画像群やサムネイル画像群を
	 * ダウンロードし、テーブルで応答する。
	 * バグ（2013年12月9日）
	 */
	@Override
	public Table table()
	{
		Reader aReader = new Reader();
		this.table = aReader.table();
		
		//this.downloadImages();
		//this.downloadThumbnails();
		return this.table;
	}
	
	/**
	 * 総理大臣の情報を記したCSVファイルの在処(URL)を文字列で応答する。
	 * 良好（2013年12月9日）
	 */
	public String url()
	{
		return this.urlStringOfCSV();
	}
	
	/**
	 * 総理大臣の情報の在処(URL)を文字列で応答するクラスメソッド。
	 * 良好（2013年12月9日）
	 */
	public static String urlString()
	{
		return "http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/CSV2HTML/PrimeMinisters/";
	}
	
	
	/**
	 * 総理大臣の情報を記したCSVファイル在処(URL)を文字列で応答するクラスメソッド。
	 * 良好（2013年12月9日）
	 */
	public static String urlStringOfCSV()
	{
		return Downloader.urlString() + "PrimeMinisters.csv";
	}
}
