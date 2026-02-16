package Gyakorlas2;

public class gyakorlas2_8String {


    public static void main(String[] args) {

//        LVL 1.
        String input = "Java Is FUN!";

/*      Print the string in all lowercase
        Print the string in all uppercase
        Print the length of the string
        Print the first and last character*/

        System.out.println("\n" + input.toLowerCase());
        System.out.println(input.toUpperCase());
        System.out.println(input.length());
        System.out.println("" + input.charAt(0) + input.charAt(input.length() - 1));
        System.out.println();


//        LVL 2.
        String sentence = "String manipulation is powerful";

   /*   Extract the word "manipulation"
        Replace "powerful" with "awesome"
        Check if the sentence contains the word "Java"*/
        System.out.println("\n" + sentence);
        String target = "manipulation";
        String extracted = sentence.substring(sentence.indexOf(target), (sentence.indexOf(target) + target.length()));
        System.out.println(extracted);
        String changed = sentence.replace("powerful", "awesome");
        System.out.println(changed);
        System.out.println(sentence.contains("Java"));

//        LVL 3. - Decoding

        String payload = "@@@HDR{len=100}:::MSG<<J#a!v#a##_i/s##!_F#U!#N>>:::SEP|||META[msg-decode-requested]|NOISE{1234}##END";

        /* This coded string is our payload to decode.
         * The real message lays between "MSG<<...>>".
         * First isolate this part,
         * then remove the noise (/,#,_,*),
         * make the message content camelCase (first word start with lowercase, others uppercase letters), (no need for spaces between words),
         * rebuild the whole message, paste in your filtered MSG<<...>> part,
         * locate the "msg-decode-requested" tag and change it to "msg-decode-done",
         * correct the length of the decoded message in the part {len=100}. (make it 3 digits)
         * Log each step to the console!
         * */

        System.out.println(payload.length());
        String messageContent = payload.substring(payload.indexOf("MSG"), payload.indexOf(">") + 2);
        System.out.println(messageContent);
        String filteredMessage = messageContent.replaceAll("#", "").replaceAll("/", "").replaceAll("_", "").replaceAll("!", "");
        System.out.println(filteredMessage);
        String camelCaseMessage = filteredMessage.replace("J", "j").replace("i", "I").replace("UN", "un");
        System.out.println(camelCaseMessage);
        String rebuiltMessage = payload.replace(messageContent, camelCaseMessage);
        System.out.println(rebuiltMessage);
        String decodeDone = rebuiltMessage.replace("msg-decode-requested", "msg-decode-done");
        System.out.println(decodeDone);
        int finalMessageLength = (decodeDone.length());
        String finalMessage = decodeDone.replace("100", "0" + String.valueOf(finalMessageLength));
        System.out.println(finalMessage);
        System.out.println(finalMessage.length());

    }
}
