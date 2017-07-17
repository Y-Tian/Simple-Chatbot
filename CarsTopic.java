/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

/**
 *
 * @author tony.tian
 */
public class Cars implements Domain {
    
    private final String[] randomResponses = {"You're asking the real questions here. Continue!", "It's a definite maybe!", "Sorry, shall I rephrase that? No.", "Making me repeat myself?"
            ,"Why so bombastic?"
            ,"You're looking very red today, sir."
            ,"Shall I bring you a glass of 'no thank you?'"
            ,"You're as purple as a cucumber!"
            ,"Yellow is the colour of my lawn."
            ,"I mean, yeah, probably."
            ,"I head car dealers have the best jobs around!"
            ,"I hate my job. How about you?"
            ,"Is my right hand bigger than my left?"
            ,"Are you sure it's a car you want?"
            ,"I'm open to surprises."
            ,"Stop! In the name of JUSTICE."
            ,"Is your name Lance?"
            ,"Oh my goody goody gosh!"
            ,"You just gave me diabetes."
            ,"Any other suggestions that aren't terrible?"
            ,"GEEZ, could you be more rude?"
            ,"I'm in this business for the $$$."};
    
    private final String[][] nouns_verbs = {{"loan", "require", "take"},
            {"car", "test", "cost"},
            {"track", "accelerate", "race"}};
    
    private final String[][] topicalResponse = {{"I mean I'll give you a loan, but expect to be bankrupt after.", "PREPARE YOUR ORGANS!"},
        {"I'm pretty sure you can't afford the car anyways, so why bother?", "Yeah, I don't think you need to know the price with that kind of salary."},
        {"It goes from 0 to 60 in the same time it takes for you to afford this.", "If you only have that much saved in your bank, I doubt the car you can afford can go racing. I do give out loans though."}};
    
    private final String[][] topicalRandom = {{"Your life requires much more than a loan, buddy.", "There's a promotion going on right now, it's called getting a lease."},
            {"What kind of car needs a test drive? Like honestly.", "I mean you could probably just buy four wheels and it'll basically be a car. Minus the engine and some easily replaceable parts."},
            {"You could probably run faster than the car you're about to buy, especially if your debt is chasing after you.", "What kind of race will you be attending? A snail race? Or a rock race?"}};

    public String getDescription() {
        return "A guy who needs a better job. Also a secret loan shark.";
    }

    public String getResponse(String statement) {
        String response = "";
        if(statement.trim().length() <= 0)
        {
            response = "Say something, please.";
        }
        else if(findKeyword(statement, "no") >= 0)
        {
                response = "Why so negative?";
        }
        else if(findKeyword(statement, "How much does") >= 0)
        {
            response = transformHowMuchDoes(statement);
        }
        else if(findKeyword(statement, "Can I") >= 0)
        {
            response = transformCanI(statement);
        }
        else if(findKeyword(statement, "I need") >= 0)
        {
            response = transformINeed(statement);
        }
        else if(findKeyword(statement, "I", 0) >= 0 && findKeyword(statement, "loan", findKeyword(statement, "I", 0)) >= 0)
        {
            response = transformILoan(statement);
        }
        else if(findKeyword(statement, "promotion") >= 0)
        {
            response = "Other branches giving out promotions, but not us. We're 'u n i q u e'.";
        }
        else if(findKeyword(statement, "sports car") >= 0)
        {
            response = "Do you play sports in it or does the car play sports?";
        }
        else if(findKeyword(statement, "super car") >= 0)
        {
            response = "Wow! I'm like so unimpressed.";
        }
        else if(findKeyword(statement, "gas") >= 0)
        {
            response = "It also runs on a hand crank, just in case.";
        }
        else if(findKeyword(statement, "crash") >= 0)
        {
            response = "Uh oh...";
        }
        else if(findKeyword(statement, "lease") >= 0)
        {
            response = "Take a number from 1 to 100 and then silently whisper to yourself: 'the lease rate is 100%";
        }
        else if(findKeyword(statement, "license") >= 0)
        {
            response = "Congrats! But I don't think you'll be needing one anytime soon.";
        }
        else if(findKeyword(statement, "drive") >= 0)
        {
            response = "You know what I like to say: drive and conquer, boys.";
        }    
        else
        {
                response = findNounVerb(statement);
        }
        return response;
    }

    public String getRandomResponse() {
        return randomResponses[(int)(Math.random() * randomResponses.length)];
    }
    
    public int findKeyword(String statement, String goal, int start)
    {
        String line = statement.trim().toLowerCase();
        String term = goal.toLowerCase();
        if(line.equals(term))
        {
            return 0;
        }
        else
        {
            int index = line.indexOf(term, start);
            while(index >= 0)
            {
                String before = " ";
                String after = " ";
                
                if(index > 0)
                {
                    before = line.substring(index - 1, index);
                }
                if(index + term.length() < line.length())
                {
                    after = line.substring(index + term.length(), index + term.length() + 1);
                }
                char cBefore = before.charAt(0);
                char cAfter = after.charAt(0);
                if((97 <= cAfter && cAfter <= 122) || (97 <= cBefore && cBefore <= 122))
                {
                    index = line.indexOf(term, index + 1);
                }
                else
                {
                    return index;
                }
            }
            return -1;
        }
    }
    
    private int findKeyword(String statement, String goal)
    {
            return findKeyword (statement, goal, 0);
    }
    
    public String transformHowMuchDoes(String statement)
    {
        String line = statement.trim();
        if(!Character.isAlphabetic(line.charAt(line.length() - 1)))
        {
            line = line.substring(0, line.length() - 1);
        }
        int index = findKeyword(line, "How much does");
        
        String ending = line.substring(index + 13).trim();
        String begin = ending.substring(0, 1).toUpperCase();
        return begin + ending.substring(1) + "s more than twice your salary.";
    }
    
    public String transformINeed(String statement)
    {
        String line = statement.trim();
        if(!Character.isAlphabetic(line.charAt(line.length() - 1)))
        {
            line = line.substring(0, line.length() - 1);
        }
        int index = findKeyword(line, "I need");
        
        String ending = line.substring(index + 6).trim();
        return "I also need " + ending + ". Are you willing to give it to me?";
    }
    
    public String transformCanI(String statement)
    {
        String line = statement.trim();
        if(!Character.isAlphabetic(line.charAt(line.length() - 1)))
        {
            line = line.substring(0, line.length() - 1);
        }
        int index = findKeyword(line, "Can I");

        String ending = line.substring(index + 5).trim();
        return "Sorry, but does your insurance company allow you to " + ending + "?";
    }
    
    public String transformILoan(String statement)
    {
        String line = statement.trim();    
        if(!Character.isAlphabetic(line.charAt(line.length() - 1)))
        {
            line = line.substring(0, line.length() - 1);
        }
        
        int psnOfI = findKeyword(line, "I", 0);
        int psnOfLoan = findKeyword(line, "loan", psnOfI + 1);
        
        String ending = line.substring(psnOfI + 1, psnOfLoan).trim();
        return "Why do you " + ending + " loan? Is your salary not good enough?";
    }
    
    public String findNounVerb(String statement)
    {
        for(int x = 0; x < nouns_verbs.length; x++)
        {
            if(findKeyword(statement, nouns_verbs[x][0]) >= 0)
            {
                for(int y = 1; y < nouns_verbs[x].length; y++)
                {
                    if(findKeyword(statement, nouns_verbs[x][y]) >= 0)
                    {
                        return topicalResponse[x][y -1];
                    }
                    else 
                    {
                        return topicalRandom[x][(int)(Math.random() * topicalRandom[x].length)];
                    }
                }
            }
        }
        return getRandomResponse();
    }
    
}
