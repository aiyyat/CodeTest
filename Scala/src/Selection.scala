import scala.collection.mutable.MutableList;
import utility.Util
case class SelectionSort(val l: List[Int]) {
	def sort():MutableList[Int] = {
		var c = Util.copy(l)
		for(i<-c.length-1 to 1 by -1) {
			var max:Int = i;
			for(j<-0 until i) {
				if(c(j)>c(max)) max = j
			}
			Util.swap(c,i,max)
		}
		c
	}
}
object SelectionSort {
	def main(a:Array[String]) :Unit = println(new SelectionSort(List(5,3,2,4,7,8,5,0)).sort)
}
