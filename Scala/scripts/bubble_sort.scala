import scala.collection.mutable.MutableList;
case class BubbleSort(val as:List[Int]) {
	def sort():MutableList[Int]= {
		var acopy =MutableList[Int]() 
		for(a <- as) acopy:+=a
		for(i<-(acopy.length-1) until 0 by -1) {
			for(j<-0 until i) {
				if(acopy(j)>acopy(j+1)) {
					Util.swap(acopy,j,j+1);
				}
			}
		}
		acopy
	}
	def main(args: Array[String]): Unit = {
	  
	}
}
object Util {
	def swap(l:MutableList[Int],x:Int,y:Int):Unit={
		var temp = l(x);
		l(x) = l(y);
		l(y) = temp;
	}
}
var b = new BubbleSort(List(3,4,6,2,1,9,6,0))
print(b.sort)
