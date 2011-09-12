                   
import java.security.MessageDigest

class BloomFilter (val size: Int) {  
	val hashCount = 5       
	var wordCount = 0
	
	val bitField = {
		new BitField(size)
	}             
	
    val hashFunc = new MurmurHashable().hashes(hashCount)(size) _ 
	
	private def hashs(item: String): Array[Int] = {  
		val word = item.toLowerCase.trim  
		hashFunc(item)
	}   
	
	def add(item: String) {                  
		hashs(item).foreach( bitField.set(_, 1)) 
		wordCount = wordCount + 1                  
	} 
	
	def contains(item:String): Boolean = {  
		var count = 0
		hashs(item).foreach( (x) => {
			if (bitField.get(x) == 1) {
				count = count + 1
			}
		})                       
		
		count == hashCount
	}
} 

trait Hashable {
	def hashes(hashCount: Int)(max: Int)(value: String): Array[Int]
}                                                                  

class MurmurHashable extends Hashable {
	def hashes(hashCount: Int)(max: Int)(value: String): Array[Int] = {
		val hash1 = MurmurHash.hash(value.getBytes, 0)
		val hash2 = MurmurHash.hash(value.getBytes, hash1)
		
		(for (i<- 0 until hashCount) yield scala.math.abs((hash1 + i*hash2) % max)).toArray
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
		              
		println(Integer.MAX_VALUE)
		val filter  = new BloomFilter(200)
		filter.add("abc")   
		println(filter.contains("abd"))
		
	}
	
}