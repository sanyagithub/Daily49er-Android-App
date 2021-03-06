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

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlSerializer;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NewsTab extends ListActivity 
{
	public static List<Message> messageList;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		getListView().setTextFilterEnabled(true);
		loadFeed();
	}
	/**
	 * Parse, format, and print article's title in list view order.
	 */
	public void loadFeed()
	{
		try
		{
			String articleTitle;
			FeedParser parser = FeedParserFactory.getParser();
			messageList = parser.parse();
			List<String> titles = new ArrayList<String>(messageList.size());
	    	for(Message article : messageList)
	    	{
	    		articleTitle = article.getTitle() + "\nCategory: " + article.getCategory(); 
	    		titles.add(articleTitle);
	    	}
	   
	    	ArrayAdapter<String> adapter = 
	    		new ArrayAdapter<String>(this, R.layout.row, titles);
	    		
	    	this.setListAdapter(adapter);
		}
		catch (Throwable t)
		{
    		Log.e("Daily49er",t.getMessage(),t);
    	}
	}

	/*
	 * When an item on the list is clicked, it saved the position ID of the item to be used later for 
	 * viewing the article.
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		super.onListItemClick(l, v, position, id);
		Intent viewMessage = new Intent(this, Article.class);
		viewMessage.putExtra("position", id);
		this.startActivity(viewMessage);

	}
}