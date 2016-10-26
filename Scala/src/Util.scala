package utility
import scala.collection.mutable.MutableList
object Util {
        def swap(l:MutableList[Int],x:Int,y:Int):Unit={
                var temp = l(x);
                l(x) = l(y);
                l(y) = temp;
        }
	def copy(l:List[Int]):MutableList[Int]={
		var m = MutableList[Int]()
		for(e<-l) m:+=e
		m
	}
}
