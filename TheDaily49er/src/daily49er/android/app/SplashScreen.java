package daily49er.android.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * This class creates a new thread for running the splash screen on the start
 * of the application.
 * @author Alex Chavez
 *
 */
public class SplashScreen extends Activity
{
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.splash);
      Thread splashThread = new Thread() 
      {
          @Override
          public void run() 
          {
             try 
             {
                int waited = 0;
                while (waited < 5000) 
                {
                   sleep(100);
                   waited += 100;
                }
             } 
             catch (InterruptedException e) 
             {
                // do nothing
             } 
             finally 
             {
                finish();
                Intent splashIntent = new Intent();
                splashIntent.setClassName("daily49er.android.app", 
                		"daily49er.android.app.Daily49er");
                startActivity(splashIntent);
             }
          }
       };
       splashThread.start();
   }
}