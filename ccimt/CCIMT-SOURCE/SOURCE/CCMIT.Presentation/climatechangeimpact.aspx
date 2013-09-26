<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="climatechangeimpact.aspx.cs" Inherits="CCIMT.Presentation.climatechangeimpact" %>

<%@ Register src="usercontrols/graphbase.ascx" tagname="graphbase" tagprefix="uc1" %>
<%@ Register src="usercontrols/graphother.ascx" tagname="graphother" tagprefix="uc2" %>

<%@ Register src="usercontrols/Download.ascx" tagname="Download" tagprefix="uc3" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">

<script type="text/javascript" language="javascript">
<!--
    $(document).ready(function () {
        $('.bubble-popup').CreateBubblePopup({

            position: 'top',
            align: 'center',

            innerHtml: 'Field Help Description <br/> \
            Field Help Description <br/> \
            Field Help Description',
            innerHtmlStyle: {
                color: '#FFFFFF',
                'text-align': 'center'
            },

            themeName: 'all-black',
            themePath: 'themes/jquerybubblepopup-themes'

        });
    });
//-->
</script>

</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

    <div id="divMain" align="center">
		<div id="divContentArea">
			
			<div id="divHeading">
				<h1>
					Climate Change Impact
				</h1>
			</div>
			
			<div id="divBreadcrumb" align="left">
                <a href="default.aspx" title="Go back to 'Climate Data'">Climate Data</a> / 
                <a href="materialproperties.aspx" title="Go to 'Material Properties'">Material Properties</a> / 
                <a href="structuredefinitions.aspx" title="Go back to 'Structure Definitions'">Structure Definitions</a> / 
                Climate Change Impact
			</div>

            <div id="divOutputMain" align="left">	
                <div id="divTabs">
                    <asp:Table BorderStyle="None" CellPadding="0" CellSpacing="0" ID="tblTabs" runat="server" ClientIDMode="Static">
                        <asp:TableRow ID="trTabs" runat="server" ClientIDMode="Static">
                        
                        </asp:TableRow>
                    </asp:Table>
                </div>

				<div id="divDownloadOutputData" style="float:right; left: -20px; top: -38px;">					
				    <uc3:Download ID="Download1" runat="server" />					
				</div>
				
				<div id="divGraphsMain">
					<div id="divGraphsBase" runat="server" clientidmode="Static">                        
                        <uc1:graphbase ID="graphbase1" runat="server" />                        
                    </div>
                    <div id="divGraphsOther" runat="server" clientidmode="Static">
                        <uc2:graphother ID="graphother1" runat="server" />
                    </div>
				</div>
				<div id="divButtons" align="left">
                    <asp:Button ID="lnkBack" runat="server" class="Buttons" Text="Back" OnClientClick="return GoBack()" ToolTip="Back to previous page." />
				</div>
			</div>	
		</div>	
	</div>

     <script type="text/javascript">
         function GoBack() {
             location.href = 'structuredefinitions.aspx';
             return false;
         }
    </script>
</asp:Content>
