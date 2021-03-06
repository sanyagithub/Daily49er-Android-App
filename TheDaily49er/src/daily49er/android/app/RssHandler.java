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
import java.util.ArrayList;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import static daily49er.android.app.BaseFeedParser. *;
public class RssHandler extends DefaultHandler{
	private List<Message> messages;
	private Message currentMessage;
	private StringBuilder builder;
	
	public List<Message> getMessages(){
		return this.messages;
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
		throws SAXException{
		super.characters(ch, start, length);
		builder.append(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String name)
		throws SAXException{
		super.endElement(uri, localName, name);
		if(this.currentMessage != null){
			if (localName.equalsIgnoreCase(TITLE)){
				currentMessage.setTitle(builder.toString());
			} else if (localName.equalsIgnoreCase(LINK)){
				currentMessage.setLink(builder.toString());
			} else if (localName.equalsIgnoreCase(DESCRIPTION)){
				currentMessage.setDescription(builder.toString());
			} else if (localName.equalsIgnoreCase(PUB_DATE)){
				currentMessage.setDate(builder.toString());
			} else if (localName.equalsIgnoreCase(AUTHOR)){
				currentMessage.setAuthor(builder.toString());
			}else if (localName.equalsIgnoreCase(CATEGORY)){
				currentMessage.setCategory(builder.toString());
			} else if (localName.equalsIgnoreCase(ITEM)){
				messages.add(currentMessage);
			}
			builder.setLength(0);	
		}
	}
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		messages = new ArrayList<Message>();
		builder = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		if (localName.equalsIgnoreCase(ITEM)){
			this.currentMessage = new Message();
		}
	}

}
