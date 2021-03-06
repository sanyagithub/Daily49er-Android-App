/*
 *	Copyright 2011 Alex Chavez, Punravee Cherngchaosil, Amanda Nguyen
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *	
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */

package daily49er.android.app;

import android.webkit.WebViewClient;
import android.webkit.WebView;

/**
 * The URL for a given WebView is loaded here. 
 * @author Alex Chavez
 */
public class LoadPage extends WebViewClient 
{
	/**
	 * Loads a web page by it's URL by overriding a URL loading instance in order to keep
	 * the WebView within the constraints of our project and prevent it from opening a 
	 * new web browser window.
	 * @param pageView - the WebView we're using to launch a new WebView intent with a
	 * given URL.
	 * @param url - the URL for a web page we want to open.
	 */
	LoadPage(WebView pageView, String url)
	{
        shouldOverrideUrlLoading(pageView, url);	
	}

	/**
	 * Gives the application control of the WebView intent to stay in the application and
	 * not open a seperate page.
	 * @param view - the WebView object that will load a web page.
	 * @param theUrl - the URL of the web page to be loaded.
	 */
	@Override
    public boolean shouldOverrideUrlLoading(WebView pageView, String theUrl)
    {
    	pageView.loadUrl(theUrl);
    	return true;
    }
}
