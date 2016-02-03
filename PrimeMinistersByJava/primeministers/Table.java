package primeministers;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

/**
 * 表：総理大臣の情報テーブル。
 * 良好（2013年12月9日）
 */
public class Table extends Object
{
	/**
	 * ここを作成してください。
	 * まず、次のページを参照しながら、スケルトン（スタブ）を作ることから始めましょう。
	 * http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/CSV2HTML/PrimeMinistersJavaDoc/index.html
	 */
	
	/**
	 * 属性リストを記憶するフィールド。
	 * 良好（2013年12月9日）
	 */
	private Attributes attributes;
	
	/**
	 * 画像群を記憶するフィールド。
	 * 良好（2013年12月9日）
	 */
	private ArrayList<BufferedImage> images;
	
	/**
	 * サムネイル画像群を記憶するフィールド。
	 * 良好（2013年12月9日）
	 */
	private ArrayList<BufferedImage> thumbnails;
	
	/**
	 * タプル群を記憶するフィールド。
	 * 良好（2013年12月9日）
	 */
	private ArrayList<Tuple> tuples;
	
	/**
	 * テーブルのコンストラクタ。
	 * 良好（2013年12月9日）
	 */
	public Table()
	{
		this.images = new ArrayList<BufferedImage>();
		this.thumbnails = new ArrayList<BufferedImage>();
		this.tuples = new ArrayList<Tuple>();
		return;
	}
	
	/**
	 * タプルを追加する。
	 * 良好（2013年12月9日）
	 */
	public void add(Tuple aTuple)
	{
		this.tuples.add(aTuple);
		return;
	}
	
	/**
	 * 属性リストを応答する。
	 * 良好（2013年12月9日）
	 */
	public Attributes attributes()
	{
		return this.attributes;
	}
	
	/**
	 * 属性リストを設定する。
	 * 良好（2013年12月9日）
	 */
	public void attributes(Attributes instanceOfAttributes)
	{
		this.attributes = instanceOfAttributes;
		return;
	}
	
	/**
	 * 画像群を応答する。
	 * 良好（2013年12月9日）
	 */
	public ArrayList<BufferedImage> images()
	{
		return this.images;
	}
	
	/**
	 * 画像またはサムネイル画像の文字列を受け取って該当画像を応答する。
	 * 良好（2013年12月9日）
	 */
	private BufferedImage picture(String aString)
	{
		return null;
	}
	
	/**
	 * サムネイル画像群を応答する。
	 * 良好（2013年12月9日）
	 */
	public ArrayList<BufferedImage> thumbnails()
	{
		return this.thumbnails;
	}
	
	/**
	 * 自分自身を文字列にして、それを応答する。
	 * 良好（2013年12月9日）
	 */
	public String toString()
	{
		StringBuffer aBuffer = new StringBuffer();
		Class aClass = this.getClass();
		aBuffer.append(aClass.getName());
		return aBuffer.toString();
	}
	
	/**
	 * タプル群を応答する。
	 * 良好（2013年12月9日）
	 */
	public ArrayList<Tuple> tuples()
	{
		return this.tuples;
	}
}
