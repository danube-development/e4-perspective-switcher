/*******************************************************************************
 * Copyright (c) 2012 - 2014 Joseph Carroll and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Joseph Carroll <jdsalingerjr@gmail.com> - initial API and implementation
 *	   Lars Vogel <Lars.Vogel@gmail.com> - ongoing maintenance 
 ******************************************************************************/ 
package org.eclipse.e4.ui.workbench.perspectiveswitcher;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCategory;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MCommandParameter;
import org.eclipse.e4.ui.model.application.commands.MCommandsFactory;
import org.eclipse.e4.ui.model.application.commands.MHandler;
import org.eclipse.e4.ui.workbench.perspectiveswitcher.commands.E4WorkbenchCommandConstants;
import org.eclipse.e4.ui.workbench.perspectiveswitcher.commands.E4WorkbenchParameterConstants;
import org.eclipse.e4.ui.workbench.perspectiveswitcher.handlers.CloseHandler;
import org.eclipse.e4.ui.workbench.perspectiveswitcher.handlers.E4WorkbenchHandlerConstants;
import org.eclipse.e4.ui.workbench.perspectiveswitcher.handlers.ShowPerspectiveHandler;
import org.eclipse.e4.ui.workbench.perspectiveswitcher.handlers.ShowTextHandler;

public class E4PerspectiveSwitcherProcessor {

	@Inject private MApplication application;
	
	MCommandsFactory commandsFactory = MCommandsFactory.INSTANCE;
	
	String contributorURI = E4PerspectiveSwitcherActivator.getDefault().getPlatformURI();
	
	@Execute
	void init() {
		// Perspectives Category definition
		MCategory perspectivesCategory = commandsFactory.createCategory();
		perspectivesCategory.setContributorURI(contributorURI);
		perspectivesCategory.setDescription(E4WorkbenchCommandConstants.CATEGORY_PERSPECTIVES$_DESCRIPTION);
		perspectivesCategory.setElementId(E4WorkbenchCommandConstants.CATEGORY_PERSPECTIVES);
		perspectivesCategory.setName(E4WorkbenchCommandConstants.CATEGORY_PERSPECTIVES$_NAME);
		application.getCategories().add(perspectivesCategory);
		
		
		{ // Show Perspective command & handler with properties
			MCommand showPerspectiveCommand = commandsFactory.createCommand();
			showPerspectiveCommand.setContributorURI(contributorURI);
			showPerspectiveCommand.setElementId(E4WorkbenchCommandConstants.PERSPECTIVES_SHOW_PERSPECTIVE);
			showPerspectiveCommand.setCategory(perspectivesCategory);
			showPerspectiveCommand.setCommandName(E4WorkbenchCommandConstants.PERSPECTIVES_SHOW_PERSPECTIVE$_NAME);
			showPerspectiveCommand.setDescription(E4WorkbenchCommandConstants.PERSPECTIVES_SHOW_PERSPECTIVE$_DESCRIPTION);
			application.getCommands().add(showPerspectiveCommand);
			
			MCommand showTextCommand = commandsFactory.createCommand();
			showTextCommand.setContributorURI(contributorURI);
			showTextCommand.setElementId(E4WorkbenchCommandConstants.PERSPECTIVES_SHOW_TEXT);
			showTextCommand.setCategory(perspectivesCategory);
			showTextCommand.setCommandName(E4WorkbenchCommandConstants.PERSPECTIVES_SHOW_TEXT$_NAME);
			showTextCommand.setDescription(E4WorkbenchCommandConstants.PERSPECTIVES_SHOW_TEXT$_DESCRIPTION);
			application.getCommands().add(showTextCommand);
			
			MCommand closeCommand = commandsFactory.createCommand();
			closeCommand.setContributorURI(contributorURI);
			closeCommand.setElementId(E4WorkbenchCommandConstants.PERSPECTIVES_CLOSE);
			closeCommand.setCategory(perspectivesCategory);
			closeCommand.setCommandName(E4WorkbenchCommandConstants.PERSPECTIVES_CLOSE$_NAME);
			closeCommand.setDescription(E4WorkbenchCommandConstants.PERSPECTIVES_CLOSE$_DESCRIPTION);
			application.getCommands().add(closeCommand);
			
			MCommandParameter parameterPerspectiveId = commandsFactory.createCommandParameter();
			parameterPerspectiveId.setContributorURI(contributorURI);
			parameterPerspectiveId.setElementId(E4WorkbenchParameterConstants.COMMAND_PERSPECTIVE_ID);
			parameterPerspectiveId.setName(E4WorkbenchParameterConstants.COMMAND_PERSPECTIVE_ID$_NAME);
			parameterPerspectiveId.setOptional(E4WorkbenchParameterConstants.COMMAND_PERSPECTIVE_ID$_OPTION);
			showPerspectiveCommand.getParameters().add(parameterPerspectiveId);
			showTextCommand.getParameters().add(parameterPerspectiveId);
			closeCommand.getParameters().add(parameterPerspectiveId);
			
			MCommandParameter parameterWindow = commandsFactory.createCommandParameter();
			parameterWindow.setContributorURI(contributorURI);
			parameterWindow.setElementId(E4WorkbenchParameterConstants.COMMAND_PERSPECTIVE_WINDOW);
			parameterWindow.setName(E4WorkbenchParameterConstants.COMMAND_PERSPECTIVE_WINDOW$_NAME);
			parameterWindow.setOptional(E4WorkbenchParameterConstants.COMMAND_PERSPECTIVE_WINDOW$_OPTION);
			showPerspectiveCommand.getParameters().add(parameterWindow);
			
			MCommandParameter parameterNewWindow = commandsFactory.createCommandParameter();
			parameterNewWindow.setContributorURI(contributorURI);
			parameterNewWindow.setElementId(E4WorkbenchParameterConstants.COMMAND_PERSPECTIVE_NEW_WINDOW);
			parameterNewWindow.setName(E4WorkbenchParameterConstants.COMMAND_PERSPECTIVE_NEW_WINDOW$_NAME);
			parameterNewWindow.setOptional(E4WorkbenchParameterConstants.COMMAND_PERSPECTIVE_NEW_WINDOW$_OPTION);
			showPerspectiveCommand.getParameters().add(parameterNewWindow);
			
			MHandler showPerspectiveHandler = commandsFactory.createHandler();
			showPerspectiveHandler.setContributorURI(contributorURI);
			showPerspectiveHandler.setElementId(E4WorkbenchHandlerConstants.PERSPECTIVES_SHOW_PERSPECTIVE);
			showPerspectiveHandler.setCommand(showPerspectiveCommand);
			showPerspectiveHandler.setContributionURI(E4PerspectiveSwitcherActivator.getDefault().getResourceURI(ShowPerspectiveHandler.class));
			application.getHandlers().add(showPerspectiveHandler);
			
			MHandler showTextHandler = commandsFactory.createHandler();
			showTextHandler.setContributorURI(contributorURI);
			showTextHandler.setElementId(E4WorkbenchHandlerConstants.PERSPECTIVES_SHOW_TEXT);
			showTextHandler.setCommand(showTextCommand);
			showTextHandler.setContributionURI(E4PerspectiveSwitcherActivator.getDefault().getResourceURI(ShowTextHandler.class));
			application.getHandlers().add(showTextHandler);
			
			MHandler closeHandler = commandsFactory.createHandler();
			closeHandler.setContributorURI(contributorURI);
			closeHandler.setElementId(E4WorkbenchHandlerConstants.PERSPECTIVES_CLOSE);
			closeHandler.setCommand(closeCommand);
			closeHandler.setContributionURI(E4PerspectiveSwitcherActivator.getDefault().getResourceURI(CloseHandler.class));
			application.getHandlers().add(closeHandler);
		}
	}
	
	
}
