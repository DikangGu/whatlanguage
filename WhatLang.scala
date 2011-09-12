import java.io.File    
import scala.io.Source                   
import scala.collection.mutable.HashMap     
import java.util.Scanner   

class WhatLang {   
	val BITFIELD_WIDTH = 2000000      
	
	val data = new HashMap[String, BloomFilter]
	
	def filterFromDictionary(filename: String): BloomFilter = {
		val bf = new BloomFilter(BITFIELD_WIDTH) 
		Source.fromFile(filename).getLines().foreach (bf.add(_))     
		bf
	}                                          
	
	def buildWordLists() {
		val dirPath = new File(".").getCanonicalPath + "/wordlists"
		new File(dirPath).listFiles().foreach( (file) => {       
			data(file.getName) = filterFromDictionary(file.getCanonicalPath)
		})    
	}           
	
	def determine(sentence: String): String = {         
		var maxScore = 0
		var lan = ""      
		
		data.foreach ((filter) => {      
			
			var score = 0
			var bf = filter._2
			sentence.split(" ").foreach( (word) => { 
				if (bf.contains(word)) {
					score = score + 1
				}
			})                       
			
			if (maxScore < score) {
				maxScore = score
				lan = filter._1
			}
		})  
		    
		println(maxScore)
		lan
	}
}
                                                     
object WhatLang {           
	
	def main(args: Array[String]) {
		val lang = new WhatLang
		lang.buildWordLists         
		val sc = new Scanner(System.in)
		while(true) {
			println(lang.determine(sc.nextLine))
		}
		
	}
}