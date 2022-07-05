//    On my honor:
//   
//    - I have not discussed the Java language code in my program with
//      anyone other than my instructor or the teaching assistants
//      assigned to this course.
//   
//    - I have not used Java language code obtained from another student,
//      or any other unauthorized source, including the Internet, either
//      modified or unmodified. 
//   
//    - If any Java language code or documentation used in my program
//      was obtained from another source, such as a text book or course
//      notes, that has been clearly noted with a proper citation in
//      the comments of my program.
//   
//    - I have not designed this program in such a way as to defeat or
//      interfere with the normal operation of the supplied grading code.
//
//    Vishesh Fogla
//    visheshfogla

/**
 * 
 * @author Vishesh Fogla (visheshfogla)
 * @version 4 May 2022.
 * 
 *          SSAD Class is the main class that consists of the main method. It
 *          takes the graph and the output file as command line parameters and
 *          passes it to table class.
 *
 */
public class SSAD {

	private static GraphTable table;

	/**
	 * The method takes graph file and output file as command line parameters, and
	 * passes them to the table class to find the shortest path.
	 * 
	 * @param args - graph file and output file
	 */
	public static void main(String[] args) {
		table = new GraphTable(args);
		table.shortestPathAlgo(args);
	}
}