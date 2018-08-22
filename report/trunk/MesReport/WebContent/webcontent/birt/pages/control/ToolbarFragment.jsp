<%-----------------------------------------------------------------------------
	Copyright (c) 2004 Actuate Corporation and others.
	All rights reserved. This program and the accompanying materials 
	are made available under the terms of the Eclipse Public License v1.0
	which accompanies this distribution, and is available at
	http://www.eclipse.org/legal/epl-v10.html
	
	Contributors:
		Actuate Corporation - Initial implementation.
-----------------------------------------------------------------------------%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page session="false" buffer="none" %>
<%@ page import="org.eclipse.birt.report.presentation.aggregation.IFragment,
				 org.eclipse.birt.report.resource.BirtResources,
				 org.eclipse.birt.report.utility.ParameterAccessor,
				 org.eclipse.birt.report.servlet.ViewerServlet" %>

<%-----------------------------------------------------------------------------
	Expected java beans
-----------------------------------------------------------------------------%>
<jsp:useBean id="fragment" type="org.eclipse.birt.report.presentation.aggregation.IFragment" scope="request" />
<jsp:useBean id="attributeBean" type="org.eclipse.birt.report.context.BaseAttributeBean" scope="request" />

<%-----------------------------------------------------------------------------
	Toolbar fragment
-----------------------------------------------------------------------------%>
<TR 
	<%
		if( attributeBean.isShowToolbar( ) )
		{
	%>
		
	<%
		}
		else
		{
	%>
		style="display:none"
	<%
		}
	%>	
>
	<TD COLSPAN='2'>
		<DIV ID="toolbar" style="text-align:center;height :40px">
					   <INPUT TYPE="button" style="border:1px solid #357ebd;height:32px;min-width:80px;border-radius:4px;background-color:#428bca;cursor pointer" value="查询" onclick="return window.parent.onQuery('#reportContainer');" />
					   <INPUT id="dftQryBtn" TYPE="button" style="display:none;border:1px solid #ddd;height:32px;min-width:80px;border-radius:4px;cursor pointer" value="高级查询" onclick="birtEventDispatcher.broadcastEvent(birtEvent.__E_PARAMETER)" />
					   <INPUT TYPE="button" style="border:1px solid #ddd;height:32px;min-width:80px;border-radius:4px;cursor pointer" value="<%= BirtResources.getHtmlMessage( "birt.viewer.toolbar.toc" )%>" onclick="birtEventDispatcher.broadcastEvent(birtEvent.__E_TOC)" />
					   <INPUT TYPE="button" style="border:1px solid #ddd;height:32px;min-width:80px;border-radius:4px;cursor pointer" onclick="return window.parent.onClear();" value="清空"/>
					   <INPUT TYPE="button" style="border:1px solid #ddd;height:32px;min-width:80px;border-radius:4px;cursor pointer" value="<%= BirtResources.getHtmlMessage( "birt.viewer.toolbar.exportreport" )%>" onclick="birtEventDispatcher.broadcastEvent( birtEvent.__E_EXPORT_REPORT )"/>
					   <INPUT TYPE="button" style="border:1px solid #ddd;height:32px;min-width:80px;border-radius:4px;cursor pointer" value="<%= BirtResources.getHtmlMessage( "birt.viewer.toolbar.export" )%>" onclick="birtEventDispatcher.broadcastEvent( birtEvent.__E_QUERY_EXPORT )"/>
					   <INPUT TYPE="button" style="border:1px solid #ddd;height:32px;min-width:80px;border-radius:4px;cursor pointer" value="<%= BirtResources.getHtmlMessage( "birt.viewer.toolbar.print" )%>" onclick="birtEventDispatcher.broadcastEvent( birtEvent.__E_PRINT )"/>
					<%
					if( ParameterAccessor.isSupportedPrintOnServer )
					{
					%>		
					<INPUT TYPE="button" style="display:none; border:1px solid #ddd;height:32px;min-width:80px;border-radius:4px;cursor pointer" value="<%= BirtResources.getHtmlMessage( "birt.viewer.toolbar.printserver" )%>" onclick="birtEventDispatcher.broadcastEvent( birtEvent.__E_PRINT_SERVER )"/>			
					   <%-- <INPUT TYPE="image" NAME='printServer' SRC="birt/images/PrintServer.gif"
					   		TITLE="<%= BirtResources.getHtmlMessage( "birt.viewer.toolbar.printserver" )%>"
					   		ALT="<%= BirtResources.getHtmlMessage( "birt.viewer.toolbar.printserver" )%>" CLASS="birtviewer_clickable"> --%>
					<%
					}
					%>										
					 
				</TR>
			</TABLE>
		</DIV>
	</TD>
</TR>
