/*******************************************************************************
 * Copyright (c) 2011 Sebastian Benz.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Sebastian Benz - initial API and implementation
 ******************************************************************************/
package de.sebastianbenz.task.impl;

import static de.sebastianbenz.task.util.Strings2.firstWord;

import java.util.regex.Pattern;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;

import de.sebastianbenz.task.Link;
import de.sebastianbenz.task.Tag;
import de.sebastianbenz.task.TextSegment;


public class CodeImplCustom extends de.sebastianbenz.task.impl.CodeImpl {

	public static final String PREFIX = "'''";
	private String lang;

	@Override
	protected String cleanText(String newText) {
		newText = newText.substring(getLang().length());
		newText = removeLeadingWhiteSpace(newText);
		newText = Pattern.compile("^" + getIntend(), Pattern.MULTILINE).matcher(newText).replaceAll("");
		newText = newText.replaceAll("\t", "  "); 
		return newText;
	}

	private String removeLeadingWhiteSpace(String text) {
		int i = 0;
		while(i < text.length() && (text.charAt(i) == '\r' || text.charAt(i) == '\n')){
			i++;
		}
		return text.substring(i);
	}

	@Override
	public String getLang() {
		if(lang == null){
			if(getText() == null){
				lang = "";
			}else if(getText().indexOf("\n") == -1){
				lang = "";
			}else{
				lang = firstWord(getText());
			}
		}
		return lang;
	}
	
	@Override
	public EList<Tag> getTags() {
		return ECollections.emptyEList();
	}
	
	@Override
	public EList<Link> getLinks() {
		return ECollections.emptyEList();
	}
	
	@Override
	public EList<TextSegment> getSegments() {
		return ECollections.emptyEList();
	}
	
	protected String removeTags(String string) {
		return string;
	}
}
