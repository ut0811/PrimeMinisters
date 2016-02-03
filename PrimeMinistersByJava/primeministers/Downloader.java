package primeministers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * ダウンローダ：総理大臣のCSVファイル・画像ファイル・サムネイル画像ファイルをダウンロードする。
 * 良好（2013年12月16日）
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
		File aFile = new File(IO.directoryOfPages(),"images");
		if(aFile.exists() == false)
		{
			aFile.mkdir();
		}
		int index = this.table.attributes().indexOfImage();
		this.downloadPictures(index);
		
		return;
	}
	
	/**
	 * 総理大臣の画像群またはサムネイル画像群をダウンロードする。
	 * 良好（2013年12月9日）
	 */
	private void downloadPictures(int indexOfPicture)
	{
		for(Tuple aTuple : this.table().tuples())
		{
			URL aURL = null;
			BufferedImage anImage = null;
			String aString = aTuple.values().get(indexOfPicture);
			try
			{
				aURL = new URL(this.urlString() + aString);
			}
			catch (MalformedURLException anException)
			{
				anException.printStackTrace();
			}
			
			try
			{
				anImage = ImageIO.read(aURL);
				System.out.println(this.urlString()+aTuple.values().get(indexOfPicture));
			}
			catch (IOException anException)
			{
				anException.printStackTrace();
			}
			
			try
			{
				ImageIO.write(anImage, "jpeg", new File(IO.directoryOfPages(),aString));
			}
			catch (IOException anException)
			{
				anException.printStackTrace();
			}
			
			if (indexOfPicture == aTuple.attributes().indexOfThumbnail())
			{
				this.table.thumbnails().add(anImage);
			}
			else
			{
				this.table.images().add(anImage);
			}
		}
		return;
	}
	
	/**
	 * 総理大臣の画像群をダウンロードする。
	 * 良好（2013年12月9日）
	 */
	public void downloadThumbnails()
	{
		File aFile = new File(IO.directoryOfPages(),"thumbnails");
		if(aFile.exists() == false)
		{
			aFile.mkdir();
		}
		int index = this.table.attributes().indexOfThumbnail();
		this.downloadPictures(index);

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
