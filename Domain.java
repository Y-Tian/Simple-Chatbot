/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

/**
 *
 * @author Cam
 */
public interface Domain
{
    /**
     * This method describes what the domain is about and its subtopics. It
     * is used by the about method of the ChatBot. Whatever description you put
     * here will be returned to the user if they ask what the chat bot can talk about
     * 
     * @return String a description of the domain (ie. Math) and its subtopics (Geometry, Algebra, etc.)
     */
    public String getDescription();

    /**
     * Given a statement, this domain will generate a response to the user through this method.
     *
     * @param Statement The statement to analyze and generate a response for
     * @return The response to the given statement, null if no response was found
     */
    public String getResponse(String Statement);

    /**
     * In the case that no response was known, the chat bot may revert to a random response.
     *
     * @return A topical random response.from this domain
     */
    public String getRandomResponse();
}
