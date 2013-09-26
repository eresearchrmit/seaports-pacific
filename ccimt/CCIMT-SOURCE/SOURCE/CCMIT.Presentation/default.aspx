<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true"
    CodeBehind="default.aspx.cs" Inherits="CCIMT.Presentation._Default" %>

<asp:Content ID="HeaderContent" runat="server" ContentPlaceHolderID="HeadContent">

    <script type="text/javascript">
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
<asp:Content ID="BodyContent" runat="server" ContentPlaceHolderID="MainContent">

    <div id="divMain" align="center">
		<div id="divContentArea">
			
			<div id="divHeading">
				<h1>Climate Data</h1>
			</div>
			
			<div id="divBreadcrumb">
				<!-- Climate Data-->
			</div>
			
			<div id="divHomeMain" align="left">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>Location</td>
						<td>
							<asp:DropDownList ID="drpLocation" runat="server" class="Dropdown" ClientIDMode="Static">
                                <asp:ListItem Value="0">Please Select</asp:ListItem>
                                <asp:ListItem Value="1">Gladstone</asp:ListItem>
                                <asp:ListItem Value="2">Port Kembla</asp:ListItem>
                                <asp:ListItem Value="3">Sydney</asp:ListItem>
                            </asp:DropDownList>
							<a href="#" class="bubble-popup">?</a>
                            <label id="lblValLocation" class="Validation Message" title="Required." style="display:none;">*</label>
						</td>
					</tr>
					<tr>
						<td>Carbon Emission Scenario</td>
						<td>
                            <asp:DropDownList ID="drpScenario" runat="server" class="Dropdown" ClientIDMode="Static">
                                <asp:ListItem Value="0">Please Select</asp:ListItem>
                                <asp:ListItem Value="1">Base</asp:ListItem>
                                <asp:ListItem Value="2">A1FI (high emission)</asp:ListItem>
                                <asp:ListItem Value="3">A1B (medium emission)</asp:ListItem>
                            </asp:DropDownList>
							<a href="#" class="bubble-popup">?</a>
                            <label id="lblValScenario" class="Validation Message" title="Required." style="display:none;">*</label>
						</td>
					</tr>
					<tr>
						<td>Climate Futures Model</td>
						<td>
                            <asp:DropDownList ID="drpModel" runat="server" class="Dropdown" ClientIDMode="Static">
                                <asp:ListItem Value="0">Please Select</asp:ListItem>
                                <asp:ListItem Value="1">Most Likely</asp:ListItem>
                                <asp:ListItem Value="2">Hotter & Drier</asp:ListItem>
                                <asp:ListItem Value="3">Cooler & Wetter</asp:ListItem>
                            </asp:DropDownList>
							<a href="#" class="bubble-popup">?</a>
                            <label id="lblValModel" class="Validation Message" title="Required." style="display:none;">*</label>
						</td>
					</tr>
                    <tr>
						<td>Year</td>
						<td>
                            <asp:DropDownList ID="drpYear" runat="server" class="Dropdown" ClientIDMode="Static">                                
                            </asp:DropDownList>
							<a href="#" class="bubble-popup">?</a>
                            <label id="lblValYear" class="Validation Message" title="Required." style="display:none;">*</label>
						</td>
					</tr>
				</table>
				
				<div id="divButtons" align="right">
                    <input type="button" value="Next" onclick="HomeNext_Click()" title="Next" />
                    <asp:Button ID="cmdNext" runat="server" Text="Next" onclick="cmdNext_Click" style="display:none;" ClientIDMode="Static" />
				</div>
			</div>	
		</div>	
	</div>

</asp:Content>
