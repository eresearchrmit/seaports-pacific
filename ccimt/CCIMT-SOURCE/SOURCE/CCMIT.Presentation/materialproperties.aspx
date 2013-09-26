<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="materialproperties.aspx.cs" Inherits="CCIMT.Presentation.materialproperties" %>

<%@ Register Src="~/usercontrols/materialpropertiesconcrete.ascx" TagName="UCMaterialPropertiesConcrete" TagPrefix="uc1" %>
<%@ Register Src="~/usercontrols/materialpropertiestimber.ascx" TagName="UCMaterialPropertiesTimber" TagPrefix="uc2" %>
<%@ Register Src="~/usercontrols/materialpropertiessteel.ascx" TagName="UCMaterialPropertiesSteel" TagPrefix="uc3" %>

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
					Material Properties
				</h1>
			</div>
			
			<div id="divBreadcrumb" align="left">
                <a href="default.aspx" title="Go back to 'Climate Data'">Climate Data</a>  
                / Material Properties
			</div>
			
            <asp:ScriptManager ID="scrMgrMatProperties" runat="server">
            </asp:ScriptManager>

			<div id="divMaterialMain" align="left">
				<div align="center">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td align="right" style="padding:10px;">
								Construction Material
							</td>
							<td align="left" style="padding:10px;">
                                <asp:DropDownList ID="drpMaterial" runat="server" class="Dropdown" ClientIDMode="Static">
                                    <asp:ListItem Selected="True" Value="0">Please Select</asp:ListItem>
                                    <asp:ListItem Value="1">Concrete</asp:ListItem>
                                    <asp:ListItem Value="2">Steel</asp:ListItem>
                                    <asp:ListItem Value="3">Timber</asp:ListItem>
                                </asp:DropDownList>
								<a href="#" class="bubble-popup">?</a>
                                <label id="lblMaterial" class="Validation Message" title="Required." style="display:none;">*</label>
							</td>
							<td>
								<asp:Button ID="btnViewEdit" runat="server" Text="View / Edit" class="Buttons" ToolTip="View/Edit data." OnClientClick="return ValidateMaterial()" onclick="btnViewEdit_Click" />
							</td>
						</tr>
					</table>
				</div>

                <div id="divViewConcrete" runat="server" visible="false" clientidmode="Static">
                    
                    <uc1:UCMaterialPropertiesConcrete ID="UCMaterialPropertiesConcrete1" runat="server" />                    
                    
				</div>

                <div id="divViewTimber" runat="server" visible="false" clientidmode="Static">
                    
                    <uc2:UCMaterialPropertiesTimber ID="UCMaterialPropertiesTimber1" runat="server" />
                    		            
                </div>

                <div id="divViewSteel" runat="server" visible="false" clientidmode="Static">

                    <uc3:UCMaterialPropertiesSteel ID="UCMaterialPropertiesSteel1" runat="server" />
                    				    
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        function GoBack() 
        {
            location.href = 'default.aspx';
            return false;
        }
    </script>
</asp:Content>
