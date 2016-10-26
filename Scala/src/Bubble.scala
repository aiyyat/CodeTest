import scala.collection.mutable.MutableList
import utility.Util
case class BubbleSort(val as:List[Int]) {
	def sort():MutableList[Int]= {
		var acopy = Util.copy(as)
		for(i<-(acopy.length-1) until 0 by -1) {
			for(j<-0 until i) {
				if(acopy(j)>acopy(j+1)) {
					Util.swap(acopy,j,j+1);
				}
			}
		}
		acopy
	}
}
object BubbleSort {
	def main(a:Array[String]) : Unit = println(new BubbleSort(List(3,4,6,2,1,9,6,0)).sort())
}
