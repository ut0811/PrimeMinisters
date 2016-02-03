package primeministers;

/**
 * 例題プログラム：総理大臣のCSVファイルをHTMLページへと変換する。
 */
public class Example extends Object
{
	/**
	 * サンプルのメインプログラム。
	 * トランスレータを生成し、総理大臣のCSVファイルをHTMLページへ変換するように依頼する。
	 * 良好（2013年12月8日）
	 */
	public static void main(String[] arguments)
	{
		Translator aTranslator = new Translator();
		aTranslator.perform();
		return;
	}
}
