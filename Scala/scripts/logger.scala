/**
 * An Application using scala to convert fully qualified package Name into printable Apache like formatted log Path
 * e.g. com.technicalyorker.bingo.test for a max length of 20 gives: c.t.bingo.test
 */
def log(str: String, n: Int) = {
  def log(str: String): String =
    {
      val split = str.split("\\.", 2)
      val a = split(0)
      if (split.length < 2) a else if (str.length() <= n) str else a.charAt(0) + "." + log(split(1))
    }
  printf("\n%d: %s", n, log(str))
}
lazy val s = { println("initialised..."); "com.technicalyorker.bingo.test" }
log(s, 20)
log(s, 4)
log(s, 5)
log(s, 1)