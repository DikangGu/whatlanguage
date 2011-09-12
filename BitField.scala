
class BitField (val size: Int) {   
	val ELEMENT_WIDTH = 32
	lazy val field = {
		println((size-1) / ELEMENT_WIDTH + 1)
		new Array[Int] ((size-1) / ELEMENT_WIDTH + 1)   
	}    
	
	def get(position: Int):Int = {   
		val t = field(position / ELEMENT_WIDTH) & (1<<(position % ELEMENT_WIDTH))
		if (t > 0) {
		 	1                                                                           
		}
		else {
			0
		}
	}    
	
	def set(position: Int, value: Int) {          
		if (value == 1) {
			field(position/ELEMENT_WIDTH) |= (1<<(position % ELEMENT_WIDTH))
		} else {                                                            
			field(position/ELEMENT_WIDTH) ^= (1<<(position % ELEMENT_WIDTH))
		}
	} 
	
	def to_s:String = {  
		
		(0 until size).foldLeft("") ((x, y) => x + get(y)) 
	}
	
}        
