                   
import java.security.MessageDigest

class BloomFilter (val size: Int) {  
	lazy val bitField = {
		new BitField(size)
	}   
	
	def hashs(item: String): List[Int] = {  
		val word = item.toLowerCase.trim
		println(word)
		List(1, 2, 3)
	}    
	
	def md5(s: String) = {
		MessageDigest.getInstance("MD5").digest(s.getBytes)
	}
	
	def add(item: String) {
		
	}
}

object BloomFilter {            
	
	def testBitFile() {   
		val field = new BitField(20)
		                              
		println(field.size)  
		field.set(19, 1)          
		field.set(7, 1)     
		field.set(0, 1)   
		println(field.to_s)
		field.set(3, 0)        
		println(field.get(3))
	}
	
	def main(args: Array[String]) {    
		
		val filter  = new BloomFilter(200)
		println(filter.hashs(" AbC"))     
		println(filter.md5("abc"))
		
	}
	
}