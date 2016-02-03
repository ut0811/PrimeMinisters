package primeministers;

import java.util.ArrayList;

/**
 * 属性群：総理大臣の情報テーブルを入出力する際の属性情報を記憶。
 */
public class Attributes extends Object
{
	/**
	 * ここを作成してください。
	 * まず、次のページを参照しながら、スケルトン（スタブ）を作ることから始めましょう。
	 * http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/CSV2HTML/PrimeMinistersJavaDoc/index.html
	 */
	
	/**
	 * 属性リストのキー群を記憶するフィールド。
	 * 良好（2013年12月9日）
	 */
	private ArrayList<String> keys;
	
	/**
	 * 属性リストの名前群を記憶するフィールド。
	 * 良好（2013年12月9日）
	 */
	private ArrayList<String> names;
	
	/**
	 * 入力用("input")または出力用("output")で属性リストを作成するコンストラクタ。
	 * 良好（2013年12月9日）
	 */
	public Attributes(String aString)
	{
		this.keys = new ArrayList<String>();
		this.names = new ArrayList<String>();
	
		if(aString == "input")
		{
			String[] aCollection = new String[]
			{
				"no", "order", "names", "kana", "period","school",
				"party", "birth", "image", "thumbnail"
			};
			
			for(String inputString : aCollection)
			{
				this.keys.add(inputString);
			}
			
		}
		else if(aString == "output")
		{
			String[] keys = new String[]
			{
				"no", "order", "names", "kana", "period","day", "school",
				"party", "birth", "image"
			};
			
			String[] names = new String[]
			{
				"人目", "代", "氏名", "ふりがな", "在位期間","在位日数", "出身校",
				"政党", "出身地", "画像"
			};
			
			for(String key : keys)
			{
				this.keys.add(key);
			}
			
			for(String name : names)
			{
				this.names.add(name);
			}
			
		}
		else
		{
			System.err.println("Attributes: 想定外の値");
		}
	}
	
	/**
	 * 指定されたインデックスに対応する名前を応答する。名前がないときはキーを応答する。
	 * 良好（2013年12月9日）
	 */
	protected String at(int index)
	{
		return this.names.get(index);
	}
	
	/**
	 * 指定されたキー文字列のインデックスを応答する。
	 * 良好（2013年12月9日）
	 */
	private int indexOf(String aString)
	{
		return this.names.indexOf(aString);
	}
	
	/**
	 * 在位日数のインデックスを応答する。
	 * 良好（2013年12月9日）
	 */
	public int indexOfDays()
	{
		return this.keys.indexOf("day");
	}
	
	/**
	 * 画像のインデックスを応答する。
	 * 良好（2013年12月9日）
	 */
	public int indexOfImage()
	{
		return this.keys.indexOf("image");
	}
	
	/**
	 * ふりがなのインデックスを応答する。
	 * 良好（2013年12月9日）
	 */
	public int indexOfKana()
	{
		return this.keys.indexOf("kana");
	}
	
	/**
	 * 氏名のインデックスを応答する。
	 * 良好（2013年12月9日）
	 */
	public int indexOfName()
	{
		return this.keys.indexOf("names");
	}
	
	/**
	 * 番号のインデックスを応答する。
	 * 良好（2013年12月9日）
	 */
	public int indexOfNo()
	{
		return this.keys.indexOf("no");
	}
	
	/**
	 * 代のインデックスを応答する。
	 * 良好（2013年12月9日）
	 */
	public int indexOfOrder()
	{
		return this.keys.indexOf("order");
	}
	
	/**
	 * 政党のインデックスを応答する。
	 * 良好（2013年12月9日）
	 */
	public int indexOfParty()
	{
		return this.keys.indexOf("party");
	}
	
	/**
	 * 在位期間のインデックスを応答する。
	 * 良好（2013年12月9日）
	 */
	public int indexOfPeriod()
	{
		return this.keys.indexOf("period");
	}
	
	/**
	 * 出身地のインデックスを応答する。
	 * 良好（2013年12月9日）
	 */
	public int indexOfPlace()
	{
		return this.keys.indexOf("birth");
	}
	
	/**
	 * 出身校のインデックスを応答する。
	 * 良好（2013年12月9日）
	 */
	public int indexOfSchool()
	{
		return this.keys.indexOf("school");
	}
	
	/**
	 * 画像のインデックスを応答する。
	 * 良好（2013年12月9日）
	 */
	public int indexOfThumbnail()
	{
		return this.keys.indexOf("thumbnail");
	}
	
	/**
	 * 指定されたインデックスに対応するキーを応答する。
	 * 良好（2013年12月9日）
	 */
	protected String keyAt(int index)
	{
		return names.get(index);
	}
	
	/**
	 * キー群を応答する。
	 * 良好（2013年12月9日）
	 */
	public ArrayList<String> keys()
	{
		return this.keys;
	}
	
	/**
	 * 指定されたインデックスに対応する名前を応答する。
	 * 良好（2013年12月9日）
	 */
	protected String nameAt(int index)
	{
		return this.names.get(index);
	}
	
	/**
	 * 名前群を応答する。
	 * 良好（2013年12月9日）
	 */
	public ArrayList<String> names()
	{
		return names;
	}
	
	/**
	 * 名前群を設定する。
	 * 良好（2013年12月9日）
	 */
	public void names(ArrayList<String> aCollection)
	{
		this.names = aCollection;
	}
	
	/**
	 * 属性リストの長さを応答する。
	 * 良好（2013年12月9日）
	 */
	public int size()
	{
		return keys.size();
	}
	
	/**
	 * 自分自身を文字列にして、それを応答する。
	 * 良好（2013年12月9日）
	 */
	@Override
	public String toString()
	{
		StringBuffer aBuffer = new StringBuffer();
		Class aClass = this.getClass();
		aBuffer.append(aClass.getName());
		aBuffer.append("[keys=");
		aBuffer.append(keys);
		aBuffer.append(",names=");
		aBuffer.append(names);
		aBuffer.append("]");
		return aBuffer.toString();
	}
}
