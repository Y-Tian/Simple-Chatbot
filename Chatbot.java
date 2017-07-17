/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author Cam
 */
public class ChatBot
{
    private ArrayList<Domain> domains;
    private Domain previouslyUsed;

    public ChatBot()
    {
        domains = new ArrayList<Domain>();
        previouslyUsed = null;
    }

    public void load(Domain d)
    {
        domains.add(d);
    }

    public String getResponse(String Statement)
    {
        final long THINK_DELAY = 2000;//2 seconds
        long now = System.currentTimeMillis();
        ListIterator<Domain> iter = domains.listIterator();
        while(iter.hasNext())
        {
            Domain next = iter.next();
            String response = next.getResponse(Statement);
            if(response != null )
            {
                previouslyUsed = next;
                return response;
            }
        }

        //at this point no suitable response was found from our domains
        //1) if we have not responded yet to any statement (previouslyUsed == null)
        //choose a random domain
        //2) Otherwise we will use the last known domain to give a random response
        if(previouslyUsed == null)
        {
            int size = domains.size();
            int randomIndex = (int)(Math.random()*size);//0 -> (size - 1)
            previouslyUsed = domains.get(randomIndex);
        }

        return previouslyUsed.getRandomResponse();
    }

    public String about()
    {
        String aboutDomains = "This chat bot has been loaded with the following domains: ";
        ListIterator<Domain> iter = domains.listIterator();
        while(iter.hasNext())
        {
            Domain next = iter.next();
            aboutDomains += "\n"+next.getDescription();
        }

        return aboutDomains;
    }
    
}
