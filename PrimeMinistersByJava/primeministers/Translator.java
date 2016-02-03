package primeministers;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * トランスレータ：総理大臣のCSVファイルをHTMLページへと変換するプログラム。
 * 良好（2013年12月9日）
 */
public class Translator extends Object
{
	/**
	 * ここを作成してください。
	 * まず、次のページを参照しながら、スケルトン（スタブ）を作ることから始めましょう。
	 * http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/CSV2HTML/PrimeMinistersJavaDoc/index.html
	 */
	
	/**
	 * CSVに由来するテーブルを記憶するフィールド
	 * 良好（2013年12月8日）
	 */
	private Table inputTable;
	
	/**
	 * HTMLに由来するテーブルを記憶するフィールド
	 * 良好（2013年12月8日）
	 */
	private Table outputTable;
	
	
	/**
	 * トランスレータのコンストラクタ。
	 * 良好（2013年12月8日）
	 */
	public Translator()
	{
		super();
		this.inputTable = new Table();
		this.outputTable = new Table();
		/*
		String days = this.computeNumberOfDays("1964年11月9日〜1972年7月7日");
		System.out.println("---- DUBUG = "+days+"---------");
		String days2 = this.computeNumberOfDays("2012年12月26日〜");
		System.out.println("---- DUBUG = "+days2+"---------");
		 */
		
		return;
	}
	
	/**
	 * 総理大臣のCSVファイルをHTMLページへ変換する。
	 * バグ（2013年12月8日）
	 */
	public void perform()
	{
		Downloader aDownload = new Downloader();
		aDownload.downloadCSV();
		this.inputTable = aDownload.table();
		
		aDownload.downloadImages();
		aDownload.downloadThumbnails();

		/* テーブルの確認用デバッガーもどき */
		//this.debugTable(); //後で消す
		
		Table aTable = this.table(this.inputTable);
		
		Writer aWriter = new Writer();
		aWriter.table(aTable);
		String aString = "総理大臣のCSVファイルからHTMLページへの変換を無事に完了しました。\n";
		JOptionPane.showMessageDialog(null, aString, "報告", JOptionPane.PLAIN_MESSAGE);
		
		return;
	}
	
	/**
	 * テープルの状態を確認するデバック用メソッド
	 * 後で消してね----------------------------!!
	 */
	private void debugTable()
	{
		for (String str : this.inputTable.attributes().keys())
		{
			System.out.print(str + " ");
		}
		
		for (Tuple aTuple : this.inputTable.tuples())
		{
			for (String aString : aTuple.values())
			{
				System.out.print(aString + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 在位日数を計算して、それを文字列にして応答する。
	 * 良好（2013年12月9日）
	 */
	public String computeNumberOfDays(String periodString)
	{
		ArrayList<String> aStringArray = IO.splitString(periodString,"〜年月日");
		//From [0]年 [1]月 [2]日 : To [3]年 [4]月 [5]日
		
		int yearFrom, monthFrom, dayFrom;
		int yearTo, monthTo, dayTo;
		
		yearFrom = Integer.parseInt(aStringArray.get(0));
		monthFrom = Integer.parseInt(aStringArray.get(1));
		dayFrom = Integer.parseInt(aStringArray.get(2));
		
		if(aStringArray.size() < 4)
		{
			Calendar aCalendar = Calendar.getInstance();
			yearTo = aCalendar.get(Calendar.YEAR);        //現在の年を取得
			monthTo = aCalendar.get(Calendar.MONTH) + 1;  //現在の月を取得
			dayTo = aCalendar.get(Calendar.DATE) + 1;     //現在の日を取得
			
		}
		else
		{
			yearTo = Integer.parseInt(aStringArray.get(3));
			monthTo = Integer.parseInt(aStringArray.get(4));
			dayTo = Integer.parseInt(aStringArray.get(5));
		}
		
		Calendar aCalendarFrom = Calendar.getInstance();
		Calendar aCalendarTo = Calendar.getInstance();
		
		aCalendarFrom.set(yearFrom, monthFrom, dayFrom);
		aCalendarTo.set(yearTo, monthTo, dayTo);
		
		long from = aCalendarFrom.getTimeInMillis();
		long to = aCalendarTo.getTimeInMillis();
		long one_date_time = 1000*60*60*24;
		
		long diffDays = (to - from)/one_date_time;
		
		String days = String.format("%s",diffDays);
		
		return days;
	}
	
	/**
	 * サムネイル画像から画像へ飛ぶためのHTML文字列を生成して、それを応答する。
	 * 良好（2013年12月8日）
	 */
	public String computeStringOfImage(String aString,Tuple aTuple,int no)
	{
		ArrayList<String> values = aTuple.values();
		
		String aNo = values.get(aTuple.attributes().indexOfNo());
		String aImage = values.get(aTuple.attributes().indexOfImage());
		String aThumbnail = values.get(aTuple.attributes().indexOfThumbnail());
		
		String imageTag = "<a name="+aNo+" href="+aImage+"><img class=\"borderless\" src="+aThumbnail+" width=\"25\" height=\"32\" alt="+aNo+".jpg></a>";
		
		return imageTag;
	}
	/**
	 * 総理大臣のCSVファイルを基にしたテーブルから、HTMLページを基にするテーブルに変換して、
	 * それを応答する。
	 * 良好（2013年12月8日）
	 */
	public Table table(Table aTable)
	{

		Table htmlTable = new Table();
		htmlTable.attributes(new Attributes("output"));
		
		ArrayList<Tuple> tuples = aTable.tuples();
		
		for (Tuple aTuple : tuples)
		{
			ArrayList<String> values = aTuple.values();
			Attributes attribute = aTuple.attributes();
			ArrayList<String> output = new ArrayList<String>();
			
			output.add(values.get(attribute.indexOfNo()));//人目
			output.add(values.get(attribute.indexOfOrder()));//代
			output.add(values.get(attribute.indexOfName()));//氏名
			output.add(values.get(attribute.indexOfKana()));//ふりがな
			output.add(values.get(attribute.indexOfPeriod()));//在位期間
			
			output.add(this.computeNumberOfDays(values.get(attribute.indexOfPeriod())));//在位日数
			
			output.add(values.get(attribute.indexOfSchool()));//出身校
			output.add(values.get(attribute.indexOfParty()));//政党
			output.add(values.get(attribute.indexOfPlace()));//出身地
			output.add(this.computeStringOfImage(null, aTuple, Integer.valueOf(values.get(attribute.indexOfNo()))));//画像
			
			Tuple htmlTuple = new Tuple(htmlTable.attributes(), output);
			htmlTable.add(htmlTuple);
		}
		
		this.outputTable = htmlTable;
		
		return this.outputTable;
	}
	
}
