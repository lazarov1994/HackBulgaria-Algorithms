import java.util.HashMap;

/**
 * @author Lazarov94
 *
 */
public class Ex2 {
	
	
	private static void decryptTheString(String input){
		char[] a = input.toCharArray();
		char[] toArray = new char[a.length];
		char[] alphabetic;
		char[] theKey;
		char[] cryptedString;
		int lenghtOfA = 0;
		int lenghtOfK = 0;
		
		
		int j = 0;
		for(int i = a.length/2 ; i < a.length; i++){
			toArray[j] = a[i];
			j++;
		}
		for(int i = 0; i < a.length/2; i++){
			toArray[j] = a[i];
			j++;
		}
		int start = 0;
		int desetici = 10;
		while(toArray[start] != '~'){
			lenghtOfA = lenghtOfA*desetici + (Character.getNumericValue(toArray[start]));
			start++;
		}
		alphabetic = new char[lenghtOfA];
		start++; // Avoiding ~ 
		int alphabeticCounter = 0;
		int start2 = start;
		while(start < lenghtOfA + start2){
			alphabetic[alphabeticCounter] = toArray[start];
			alphabeticCounter++;
			start++;
		}
		int startFromEnd = toArray.length-1;
		int decimals = 1;
		while(toArray[startFromEnd] != '~'){
			lenghtOfK = lenghtOfK + (Character.getNumericValue(toArray[startFromEnd]))*decimals;
			decimals *= 10;
			startFromEnd--;
		}
		startFromEnd--;
		startFromEnd = startFromEnd - lenghtOfK;// to avoid ~ 
		theKey = new char[lenghtOfK];
		cryptedString = new char[startFromEnd - start + 1];
		int cS = 0;
		while(start <= startFromEnd){
			cryptedString[cS] = toArray[start];
			start++;
			cS++;
		}
		int tK = 0;
		while(toArray[start] != '~'){
			theKey[tK] = toArray[start];
			start++;
			tK++;
		}
		HashMap<Character, Integer> lettersAndNumbers = new HashMap<Character, Integer>();
		HashMap<Integer, Character> numbersAndLetters = new HashMap<Integer, Character>();
		
		for(int i = 0; i < lenghtOfA; i++){
			lettersAndNumbers.put(alphabetic[i], i);
			numbersAndLetters.put(i, alphabetic[i]);
		}
		
		char[] theOriginalMessage = new char[cryptedString.length];
		int local = 0;
		int keyPossition = 0;
		for(int i = 0; i < cryptedString.length; i++){
			local = lettersAndNumbers.get(cryptedString[i]) - lettersAndNumbers.get(theKey[keyPossition]);
			if(local >= 0){
				theOriginalMessage[i] = numbersAndLetters.get(local);
			} else {
				local = lettersAndNumbers.get(cryptedString[i]) + lenghtOfA - lettersAndNumbers.get(theKey[keyPossition]);
				theOriginalMessage[i] = numbersAndLetters.get(local);
			}
			keyPossition++;
			if(keyPossition == lenghtOfK){
				keyPossition = 0;
			}
			
		}
		System.out.println(new String(theOriginalMessage));
	
	}
	

	public static void main(String[] args) {
		String a = "TJKUMbUUJTIREuKOXD'HR.sTOVSWU!KSJ(O.sVYtWZTTZVULsNOBdYONXFsvEu(PgWJsRTSVsYKOfDZOJSNVWu(IU!yAaMs?OW.tYaVET.A IQXTMQURJ.HLs'VHa'OTYUSzCQ!SePzsuMTzYQ!SM!NOdOH SuPMa)yA!LsKOPEUM,VAaMs.SuKOkDJEcIIStHACKBULGARIA~1260~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .',!?()rEPNtg,yTYMsJOFOtkZ sd EKsVYtXPIOUMs HK't(PYSROEAsq.JfPyAJ HVRVCUYaZsPITzMQ'UMZTEJXANEBCUYWRI!Os.Su(IkD!OdADLCKNFXDZOJ THRVCQdZMRRUMPNIDtISGTJQSz";
		long start = System.nanoTime();
		decryptTheString(a);
		System.out.println(System.nanoTime() - start);
	}
	// About the message : I decided to start programming cause I was good at maths and I didn't want to become math teacher. HA GOT CHA. I like when things don't go wrong. Success is one of my biggest motivations.
	// My expectations of the Algorithm course: I think it will be a very useful course and very interesting.
	// PS: That message was the hardest task of all lol :D I will write it on word doc, or something, with the details.
}
