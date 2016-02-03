package primeministers;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * リーダ：総理大臣の情報を記したCSVファイルを読み込んでテーブルに仕立て上げる。
 * 良好（2013年12月9日）
 */
public class Reader extends IO
{
	/**
	 * ここを作成してください。
	 * まず、次のページを参照しながら、スケルトン（スタブ）を作ることから始めましょう。
	 * http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/CSV2HTML/PrimeMinistersJavaDoc/index.html
	 */
	
	/**
	 * 総理大臣の情報を記したCSVファイルを記憶するフィールド。
	 * 良好（2013年12月8日）
	 */
	private File filename;
	
	/**
	 * リーダのコンストラクタ。
	 * 良好（2013年12月8日）
	 */
	public Reader()
	{
		this.filename = new File(IO.directoryOfPages(),"PrimeMinisters.csv");
		return;
	}
	
	/**
	 * ダウンロードしたCSVファイルを応答する。
	 * 良好（2013年12月8日）
	 */
	public File filename()
	{
		return this.filename;
	}
	
	/**
	 * ダウンロードしたCSVファイルのローカルなファイルを応答するクラスメソッド。
	 * 良好（2013年12月8日）
	 */
	public File filenameOfCSV()
	{
		return this.filename.getAbsoluteFile();
	}
	
	/**
	 * ダウンロードしたCSVファイルを読み込んでテーブルを応答する。
	 * 良好（2013年12月8日）
	 */
	public Table table()
	{
		boolean isFirstLine = true;
		Table inputTable = new Table();
		ArrayList<String> aCollection = IO.readTextFromFile(this.filename);
		
		for (String aString : aCollection)
		{
			ArrayList<String> aRaw = IO.splitString(aString, ",");
			if(isFirstLine)
			{
				inputTable.attributes(new Attributes("input"));
				inputTable.attributes().names(aRaw);
				isFirstLine = false;
			}
			else
			{
				Tuple inputTuple = new Tuple(inputTable.attributes(), aRaw);
				inputTable.add(inputTuple);
			}
		}
		return inputTable;
	}
}
