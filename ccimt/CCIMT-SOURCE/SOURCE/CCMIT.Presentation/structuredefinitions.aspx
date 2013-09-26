<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="structuredefinitions.aspx.cs" Inherits="CCIMT.Presentation.structuredefinitions" %>

<%@ Register Assembly="AjaxControlToolkit" Namespace="AjaxControlToolkit" TagPrefix="cc1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">

    <script type="text/javascript" src="scripts/bubble-popup-messages.js" ></script>   

</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <cc1:ToolkitScriptManager ID="ScriptManager1" runat="server"></cc1:ToolkitScriptManager>
    <div id="divMain" align="center">
        <div id="divContentArea">
			
			<div id="divHeading">
				<h1>
					Structure Definitions
				</h1>
			</div>
			
			<div id="divBreadcrumb" align="left">
                <a href="default.aspx" title="Go back to 'Climate Data'">Climate Data</a> / 
                <a href="materialproperties.aspx" title="Go to 'Material Properties'">Material Properties</a> / 
                Structure Definitions
			</div>
		
			<div id="divMaterialMain" align="left">

                <%--<input type="reset" value="Upload Input Data" class="Buttons AutoSize" onclick="document.getElementById('updProps').click();" title="Continue to next page." />
            	<input id="updProps" type="file" class="Upload" style="display:none;"/>--%>

                <div id="divUploadInputData">
                    <input type="button" class="Buttons AutoSize" value="Upload Input Data" onclick="ShowUpload()" />
                    <asp:Label ID="lblInputDataMessage" runat="server" ForeColor="Red"></asp:Label>
                </div>
            	<hr />

            	<div id="divHeader2Alt">
            		Material: <asp:Label ID="lblMaterialType" runat="server" Text=""></asp:Label>
            	</div>            	

            	<div id="divViewConcrete" runat="server" visible="false" clientidmode="Static">

                    <div id="divDetailConcrete" runat="server" clientidmode="Static" class="AssetDetailDiv">
                        <h4>
                            <label id="lblDetailHeadingConcrete" runat="server" clientidmode="Static"></label>
                        </h4>

                        <table id="tblDetailConcrete" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="200px">
                                    Asset Code
                                </td>
                                <td>
                                    <asp:TextBox ID="txtFTAssetCode" runat="server" class="Textbox" MaxLength="6"></asp:TextBox>
                                    <a href="#" class="bubble-popup-con_assetcode">?</a>
                                </td>
                                <td width="100px">
                                    <asp:RequiredFieldValidator ID="rfvAssetCode" ControlToValidate="txtFTAssetCode" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regFTAssetCode" ControlToValidate="txtFTAssetCode" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^[a-zA-Z0-9]+$"></asp:RegularExpressionValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Year Built
                                </td>
                                <td>
                                    <asp:TextBox ID="txtFTYearBuilt" runat="server" class="Textbox" MaxLength="4"></asp:TextBox>
                                    <a href="#" class="bubble-popup-con_yearbuilt">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvYearBuilt" ControlToValidate="txtFTYearBuilt" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regYearBuilt" ControlToValidate="txtFTYearBuilt" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^([1][9]\d\d|200[0-9]|201[0-2])$"></asp:RegularExpressionValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Description
                                </td>
                                <td>
                                    <asp:TextBox ID="txtFTDescription" runat="server" class="Textbox" MaxLength="100"></asp:TextBox>
                                    <a href="#" class="bubble-popup-con_description">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvDescription" ControlToValidate="txtFTDescription" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <%--<asp:RegularExpressionValidator ID="regFTDescription" ControlToValidate="txtFTDescription" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$"></asp:RegularExpressionValidator>--%>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Zone
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlZone" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">atmospheric</asp:ListItem>
                                        <asp:ListItem Value="2">tidal</asp:ListItem>
                                        <asp:ListItem Value="3">splash</asp:ListItem>
                                        <asp:ListItem Value="4">submerged</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-con_zone">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvZone" ControlToValidate="ddlZone" InitialValue="0" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Distance from coast
                                </td>
                                <td>
                                    <asp:TextBox ID="txtFTDistFromCost" runat="server" class="Textbox" MaxLength="5" AutoCompleteType="Disabled"></asp:TextBox>
                                    <a href="#" class="bubble-popup-con_dfc">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvDistFromCost" ControlToValidate="txtFTDistFromCost" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regDistFromCost" ControlToValidate="txtFTDistFromCost" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Exposure
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlExposure" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">B1</asp:ListItem>
                                        <asp:ListItem Value="2">B2</asp:ListItem>
                                        <asp:ListItem Value="3">C1</asp:ListItem>
                                        <asp:ListItem Value="4">C2</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-con_exposure">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvExposure" ControlToValidate="ddlExposure" InitialValue="0" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Carbonation
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlCarbonation" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">CB1</asp:ListItem>
                                        <asp:ListItem Value="2">CB2</asp:ListItem>
                                        <asp:ListItem Value="3">CB3</asp:ListItem>
                                        <asp:ListItem Value="4">CB4</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-con_carbonation">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvCarbonation" ControlToValidate="ddlCarbonation" InitialValue="0" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Chloride
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlChloride" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">CL1</asp:ListItem>
                                        <asp:ListItem Value="2">CL2</asp:ListItem>
                                        <asp:ListItem Value="3">CL3</asp:ListItem>
                                        <asp:ListItem Value="4">CL4</asp:ListItem>
                                        <asp:ListItem Value="5">CL5</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-con_chloride">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvChloride" ControlToValidate="ddlChloride" InitialValue="0" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>                            
                            <tr>
                                <td>
                                    Cover
                                </td>
                                <td>
                                    <asp:TextBox ID="txtFTCover" runat="server" class="Textbox" MaxLength="3"></asp:TextBox>
                                    <a href="#" class="bubble-popup-con_cover">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvCover" ControlToValidate="txtFTCover" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regCover" ControlToValidate="txtFTCover" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$"></asp:RegularExpressionValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    D<sub>member</sub>
                                </td>
                                <td>
                                    <asp:TextBox ID="txtFTDMember" runat="server" class="Textbox" MaxLength="5"></asp:TextBox>
                                    <a href="#" class="bubble-popup-con_dmember">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvMember" ControlToValidate="txtFTDMember" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regD" ControlToValidate="txtFTDMember" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$"></asp:RegularExpressionValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    f'<sub>c</sub>
                                </td>
                                <td>
                                    <asp:TextBox ID="txtFTF1C" runat="server" class="Textbox" MaxLength="3"></asp:TextBox>
                                    <a href="#" class="bubble-popup-con_fc">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvF1C" ControlToValidate="txtFTF1C" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regFc" ControlToValidate="txtFTF1C" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$"></asp:RegularExpressionValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    w/c
                                </td>
                                <td>
                                    <asp:TextBox ID="txtFTWC" runat="server" class="Textbox" MaxLength="5"></asp:TextBox>
                                    <a href="#" class="bubble-popup-con_wc">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvWC" ControlToValidate="txtFTWC" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regWC" ControlToValidate="txtFTWC" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    C<sub>e</sub>
                                </td>
                                <td>
                                    <asp:TextBox ID="txtFTCe" runat="server" class="Textbox" MaxLength="4"></asp:TextBox>
                                    <a href="#" class="bubble-popup-con_ce">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvCe" ControlToValidate="txtFTCe" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regCe" ControlToValidate="txtFTCe" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$"></asp:RegularExpressionValidator>
                                </td>
                            </tr>                            
                            <tr>
                                <td>
                                    D<sub>bar
                                </td>
                                <td>
                                    <asp:TextBox ID="txtFTDBar" runat="server" class="Textbox" MaxLength="3"></asp:TextBox>
                                    <a href="#" class="bubble-popup-con_dbar">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvDBar" ControlToValidate="txtFTDBar" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regDbar" ControlToValidate="txtFTDBar" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$"></asp:RegularExpressionValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    x<sub>c</sub>(t<sub>0</sub>)
                                </td>
                                <td>
                                    <asp:TextBox ID="txtFTXcTo" runat="server" class="Textbox" MaxLength="4"></asp:TextBox>
                                    <a href="#" class="bubble-popup-con_xct0">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvXcTo" ControlToValidate="txtFTXcTo" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regx2" ControlToValidate="txtFTXcTo" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <asp:Button ID="btnAddConcreteAsset" runat="server" class="Buttons" Text="Save" onclick="btnAddConcreteAsset_Click" ToolTip="Add Asset to Inputs." ValidationGroup="Asset" />
                                    &nbsp;
                                    <input id="ipClearConcrete" type="reset" class="Buttons" value="Clear" />
                                    <asp:Button ID="btnClearConcreteAsset" ClientIDMode="Static" runat="server" Text="Clear Form" OnClick="btnClearConcreteAsset_Click" style="display:none;"/>
                                </td>
                                <td>

                                </td>
                            </tr>
                        </table>
                    </div>
                                    
                    <div id="divSpacer"></div>

                    <div id="divGridConcrete" runat="server" clientidmode="Static">
                        <div>
                            <a href="#New" onclick="AddNewAssetConcrete();">Add New Asset</a>                        
                        </div>

                        <div id="divSpacer"></div>

                        <table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <th>Asset Code</th>
                                <th>Year built</th>
                                <th>Description</th>
                                <th>Zone</th>
                                <th>Distance from coast</th>
                                <th>Exposure</th>
                                <th>Carbonation</th>
                                <th>Chloride</th>
                                <%--<th>P</th>--%>
                                <th>Cover</th>
                                <th>D<sub>member</sub></th>
                                <th>f'<sub>c</sub></th>
                                <th>w/c</th>
                                <th>C<sub>e</sub></th>
                                <th>D<sub>bar</sub></th>
                                <th>x<sub>c</sub>(t<sub>0</sub>)</th>
                                <th></th>
                            </tr>     
                            <asp:ListView ID="lstAssetsConcrete" runat="server" AllowPaging="false" AutoGenerateColumns="false" DataKeyNames="AssetCode" >
                                <ItemTemplate>
                                    <tr>
                                        <td><%# Eval("AssetCode") %></td>
                                        <td><%# Eval("YearBuilt") %></td>
                                        <td><%# Eval("Description") %></td>
                                        <td><%# Eval("Zone") %></td>
                                        <td><%# Eval("DistFromCost") %></td>
                                        <td><%# Eval("Exposure") %></td>
                                        <td><%# Eval("Carbonation") %></td>
                                        <td><%# Eval("Chloride") %></td>
                                        <%--<td><%# Eval("P") %></td>--%>
                                        <td><%# Eval("Cover") %></td>
                                        <td><%# Eval("DMember") %></td>
                                        <td><%# Eval("F1C") %></td>
                                        <td><%# Eval("WC") %></td>
                                        <td><%# Eval("Ce") %></td>
                                        <td><%# Eval("DBar") %></td>
                                        <td><%# Eval("XcTo") %></td>
                                        <td>
                                            <div id="divGridButtons">
                                                <asp:ImageButton ID="btnEditConcreteAsset" ImageUrl="~/images/Article.png" runat="server" onclick="btnEditConcreteAsset_Click" 
                                                    ToolTip="Edit Asset" CommandArgument='<%# Eval("AssetCode") %>'/>
                                                &nbsp;
                                                <asp:ImageButton ID="btnDeleteConcreteAsset" ImageUrl="~/images/cmdDelete.png" runat="server" onclick="btnDeleteConcreteAsset_Click" 
                                                    ToolTip="Delete Asset" CommandArgument='<%# Eval("AssetCode") %>' OnClientClick='return Confirm("Are you sure you want to delete this asset?", 0, this);'/>
                                            </div>            
                                        </td>
                                    </tr>
                                </ItemTemplate>
                            </asp:ListView>                        
                        </table>
                    </div>
				</div>

				<div id="divViewTimber" runat="server" visible="false" clientidmode="Static">
                    
                    <div id="divDetailTimber" runat="server" clientidmode="Static" class="AssetDetailDiv">
                        <h4>
                            <label id="lblDetailHeadingTimber" runat="server" clientidmode="Static"></label>
                        </h4>

                        <table id="tblDetailTimber" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="200px">
                                    Asset Code
                                </td>
                                <td>
                                    <asp:TextBox ID="txtTAssetCode" runat="server" class="Textbox" MaxLength="6"></asp:TextBox>
                                    <a href="#" class="bubble-popup-tim_assetcode">?</a>
                                </td>
                                <td width="100px">
                                    <asp:RequiredFieldValidator ID="rfvTAssetCode" ControlToValidate="txtTAssetCode" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="revTAssetCode" ControlToValidate="txtTAssetCode" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" 
                                        ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^[a-zA-Z0-9]+$"></asp:RegularExpressionValidator>
                            </td>
                            </tr>
                            <tr>
                                <td>
                                    Year installed
                                </td>
                                <td>
                                    <asp:TextBox ID="txtTYearInstalled" runat="server" class="Textbox" MaxLength="4"></asp:TextBox>
                                    <a href="#" class="bubble-popup-tim_yearinstalled">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvTYearInstalled" ControlToValidate="txtTYearInstalled" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regTYearInstalled" ControlToValidate="txtTYearInstalled" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" 
                                        ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^([1][9]\d\d|200[0-9]|201[0-2])$"></asp:RegularExpressionValidator>
                            </td>
                            </tr>
                            <tr>
                                <td>
                                    Description
                                </td>
                                <td>
                                    <asp:TextBox ID="txtTDescription" runat="server" class="Textbox" MaxLength="100"></asp:TextBox>
                                    <a href="#" class="bubble-popup-tim_description">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvTDescription" ControlToValidate="txtTDescription" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Type
                                </td>
                                <td>
                                    <asp:TextBox ID="txtTType" runat="server" class="Textbox" MaxLength="100"></asp:TextBox>
                                    <a href="#" class="bubble-popup-tim_type">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvTType" ControlToValidate="txtTType" InitialValue="0" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <%--<asp:RegularExpressionValidator ID="rgvTType" ControlToValidate="txtTType" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" 
                                        ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="[^a-zA-Z0-9\s]"></asp:RegularExpressionValidator>--%>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    HW
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlTHW" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">HW1</asp:ListItem>
                                        <asp:ListItem Value="2">HW2</asp:ListItem>
                                        <asp:ListItem Value="3">HW3</asp:ListItem>
                                        <asp:ListItem Value="4">HW4</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-tim_hw">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvTHW" ControlToValidate="ddlTHW" InitialValue="0" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    SW
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlTSW" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">SPSU</asp:ListItem>
                                        <asp:ListItem Value="2">SPSCR1</asp:ListItem>
                                        <asp:ListItem Value="3">SPSCR2</asp:ListItem>
                                        <asp:ListItem Value="4">SPSCC1</asp:ListItem>
                                        <asp:ListItem Value="5">SPSCC2</asp:ListItem>
                                        <asp:ListItem Value="6">SPSCC3</asp:ListItem>
                                        <asp:ListItem Value="7">SPSCC4</asp:ListItem>
                                        <asp:ListItem Value="8">SPSDBT</asp:ListItem>
                                        <asp:ListItem Value="9">SPHU</asp:ListItem>
                                        <asp:ListItem Value="10">SPHCR1</asp:ListItem>
                                        <asp:ListItem Value="11">SPHCR2</asp:ListItem>
                                        <asp:ListItem Value="12">SPHCC1</asp:ListItem>
                                        <asp:ListItem Value="13">SPHCC2</asp:ListItem>
                                        <asp:ListItem Value="14">SPHCC3</asp:ListItem>
                                        <asp:ListItem Value="15">SPHDBT</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-tim_sw">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvTSW" ControlToValidate="ddlTSW" InitialValue="0" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    D<sub>o</sub>(mm)
                                </td>
                                <td>
                                    <asp:TextBox ID="txtTDo" runat="server" class="Textbox" MaxLength="5"></asp:TextBox>
                                    <a href="#" class="bubble-popup-tim_d0">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvTDo" ControlToValidate="txtTDo" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" 
                                        ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regTDo" ControlToValidate="txtTDo" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" 
                                        ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$"></asp:RegularExpressionValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    d<sub>sap</sub>(mm)
                                </td>
                                <td>
                                    <asp:TextBox ID="txtTDSap" runat="server" class="Textbox" MaxLength="5"></asp:TextBox>
                                    <a href="#" class="bubble-popup-tim_dsap">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvTDSap" ControlToValidate="txtTDSap" runat="server" ErrorMessage="Required" CssClass="Message NoMargin" 
                                        ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regTDSap" ControlToValidate="txtTDSap" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" 
                                        ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>
                                </td>
                            </tr>                            
                            <tr>
                                <td>
                                    D<sub>replace</sub>
                                </td>
                                <td>
                                    <asp:TextBox ID="txtTDReplace" runat="server" class="Textbox" MaxLength=3></asp:TextBox>
                                    <a href="#" class="bubble-popup-tim_dreplace">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvTDReplace" ControlToValidate="txtTDReplace" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regTDReplace" ControlToValidate="txtTDReplace" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" 
                                        ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$"></asp:RegularExpressionValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Maintenance
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlTMaintenance" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">MTN1</asp:ListItem>
                                        <asp:ListItem Value="2">MTN2</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-tim_maintn">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvTMaintn" ControlToValidate="ddlTMaintenance" InitialValue="0" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Contact
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlTContact" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">CNT1</asp:ListItem>
                                        <asp:ListItem Value="2">CNT2</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-tim_contact">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvTContact" ControlToValidate="ddlTContact" InitialValue="0" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Knot
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlTKnot" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">KNT1</asp:ListItem>
                                        <asp:ListItem Value="2">KNT2</asp:ListItem>
                                        <asp:ListItem Value="3">KNT3</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-tim_knot">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvTKnot" ControlToValidate="ddlTKnot" InitialValue="0" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Zone
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlTZone" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">A</asp:ListItem>
                                        <asp:ListItem Value="2">B</asp:ListItem>
                                        <asp:ListItem Value="3">C</asp:ListItem>
                                        <asp:ListItem Value="4">D</asp:ListItem>
                                        <asp:ListItem Value="5">E</asp:ListItem>
                                        <asp:ListItem Value="6">F</asp:ListItem>
                                        <asp:ListItem Value="7">G</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-tim_zone">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvTZone" ControlToValidate="ddlTZone" InitialValue="0" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Exposure
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlTExposure" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">EXP1</asp:ListItem>
                                        <asp:ListItem Value="2">EXP2</asp:ListItem>                
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-tim_exposure">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvTExposure" ControlToValidate="ddlTExposure" InitialValue="0" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <asp:Button ID="btnAddTimberAsset" runat="server" class="Buttons" Text="Save" onclick="btnAddTimberAsset_Click" ToolTip="Add Asset to Inputs." ValidationGroup="Asset" />
                                    &nbsp;
                                    <input id="ipClearTimber" type="reset" class="Buttons" value="Clear" />
                                    <asp:Button ID="btnClearTimberAsset" ClientIDMode="Static" runat="server" Text="Clear Form" OnClick="btnClearTimberAsset_Click" style="display:none;"/>
                                </td>
                                <td>
                                </td>
                            </tr>   
                        </table>
                    </div>

                    <div id="divSpacer"></div>

                    <div id="divGridTimber" runat="server" clientidmode="Static">
                        <div>
                            <a href="#New" onclick="AddNewAssetTimber();">Add New Asset</a>                        
                        </div>

                        <div id="divSpacer"></div>       

                        <table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <th>Asset Code</th>
                                <th>Year installed</th>
                                <th>Description</th>
                                <th>Type</th>
                                <th>HW</th>
                                <th>SW</th>
                                <th>D<sub>o</sub><br/>(mm)</th>
                                <th>d<sub>sap</sub><br/>(mm)</th>
                                <th>D<sub>replace</sub></th>
                                <th>Maintn</th>
                                <th>Contact</th>
                                <th>Knot</th>
                                <th>Zone</th>        
                                <th>Exposure</th>        
                                <th></th>
                            </tr>     
                            <asp:ListView ID="lstAssetsTimber" runat="server" AllowPaging="false" AutoGenerateColumns="false" DataKeyNames="AssetCode" >
                                <ItemTemplate>
                                    <tr>
                                        <td><%# Eval("AssetCode") %></td>
                                        <td><%# Eval("YearInstalled")%></td>
                                        <td><%# Eval("Description") %></td>
                                        <td><%# Eval("Type") %></td>                
                                        <td><%# Eval("HW") %></td>
                                        <td><%# Eval("SW") %></td>
                                        <td><%# Eval("Do")%></td>
                                        <td><%# Eval("DSap")%></td>
                                        <td><%# Eval("DReplace")%></td>
                                        <td><%# Eval("Maintenance")%></td>
                                        <td><%# Eval("Contact")%></td>                
                                        <td><%# Eval("Knot")%></td>                
                                        <td><%# Eval("Zone") %></td>
                                        <td><%# Eval("Exposure") %></td>                
                                        <td>
                                            <div id="divGridButtons">
                                                <asp:ImageButton ID="btnEditTimberAsset" ImageUrl="~/images/Article.png" runat="server" onclick="btnEditTimberAsset_Click" 
                                                    ToolTip="Edit Asset" CommandArgument='<%# Eval("AssetCode") %>'/>
                                                &nbsp;
                                                <asp:ImageButton ID="btnDeleteTimberAsset" ImageUrl="~/images/cmdDelete.png" runat="server" onclick="btnDeleteTimberAsset_Click" 
                                                    ToolTip="Delete Asset" CommandArgument='<%# Eval("AssetCode") %>' OnClientClick='return Confirm("Are you sure you want to delete this asset?", 0, this);'/>
                                            </div>                                      
                                        </td>
                                    </tr>
                                </ItemTemplate>
                            </asp:ListView>                            
                        </table>
                    </div>
                </div>
				
				<div id="divViewSteel" runat="server" visible="false" clientidmode="Static">

                    <div id="divDetailSteel" runat="server" clientidmode="Static" class="AssetDetailDiv">
                        <h4>
                            <label id="lblDetailHeadingSteel" runat="server" clientidmode="Static"></label>
                        </h4>

                        <table id="tblDetailSteel" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="200px">
                                    Asset Code
                                </td>
                                <td>
                                    <asp:TextBox ID="txtSAssetCode" runat="server" class="Textbox" MaxLength="6"></asp:TextBox>
                                    <a href="#" class="bubble-popup-stl_assetcode">?</a>
                                </td>
                                <td width="100px">
                                    <asp:RequiredFieldValidator ID="rfvSAssetCode" ControlToValidate="txtSAssetCode" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="RegularExpressionValidator1" ControlToValidate="txtSAssetCode" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" 
                                        ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^[a-zA-Z0-9]+$"></asp:RegularExpressionValidator>
                            </td>
                            </tr>
                            <tr>
                                <td>
                                    Year built
                                </td>
                                <td>
                                    <asp:TextBox ID="txtSYearBuilt" runat="server" class="Textbox" MaxLength="4"></asp:TextBox>
                                    <a href="#" class="bubble-popup-stl_yearbuilt">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvSYearBuilt" ControlToValidate="txtSYearBuilt" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regSYearBuilt" ControlToValidate="txtSYearBuilt" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" 
                                        ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^([1][9]\d\d|200[0-9]|201[0-2])$"></asp:RegularExpressionValidator>
                            </td>
                            </tr>
                            <tr>
                                <td>
                                    Description
                                </td>
                                <td>
                                    <asp:TextBox ID="txtSDescription" runat="server" class="Textbox" MaxLength="100"></asp:TextBox>
                                    <a href="#" class="bubble-popup-stl_description">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvSDescription" ControlToValidate="txtSDescription" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
							<tr>
                                <td>
                                    Zone
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlSZone" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">atmospheric</asp:ListItem>
                                        <asp:ListItem Value="2">tidal</asp:ListItem>
                                        <asp:ListItem Value="3">splash</asp:ListItem>
                                        <asp:ListItem Value="4">submerged</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-stl_zone">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvSZone" ControlToValidate="ddlSZone" InitialValue="0" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
							<tr>
                                <td>
                                    Hazard zone
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlSHazardZone" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">A</asp:ListItem>
                                        <asp:ListItem Value="2">B</asp:ListItem>
                                        <asp:ListItem Value="3">C</asp:ListItem>
                                        <asp:ListItem Value="4">D</asp:ListItem>
										<asp:ListItem Value="5">E</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-stl_hazardzone">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvSHazardZone" ControlToValidate="ddlSHazardZone" InitialValue="0" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
							<tr>
                                <td>
                                    Coastal zone
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlSCoastalZone" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">C1</asp:ListItem>
                                        <asp:ListItem Value="2">C2</asp:ListItem>
                                        <asp:ListItem Value="3">C3</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-stl_coastalzone">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvSCoastalZone" ControlToValidate="ddlSCoastalZone" InitialValue="0" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
							<tr>
                                <td>
                                    Coastal exposure
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlSCoastalExposure" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">EXP1</asp:ListItem>
                                        <asp:ListItem Value="2">EXP2</asp:ListItem>
                                        <asp:ListItem Value="3">EXP3</asp:ListItem>
										<asp:ListItem Value="4">EXP4</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-stl_coastalexposure">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvSCoastalExposure" ControlToValidate="ddlSCoastalExposure" InitialValue="0" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
							<tr>
                                <td>
                                    Site classification
                                </td>
                                <td>
                                    <asp:DropDownList ID="ddlSSiteClassification" runat="server" class="Dropdown">
                                        <asp:ListItem Value="0">Please Select</asp:ListItem>
                                        <asp:ListItem Value="1">SITE1</asp:ListItem>
                                        <asp:ListItem Value="2">SITE2</asp:ListItem>
                                        <asp:ListItem Value="3">SITE3</asp:ListItem>
										<asp:ListItem Value="4">SITE4</asp:ListItem>
                                    </asp:DropDownList>
                                    <a href="#" class="bubble-popup-stl_siteclassification">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvSSiteClassification" ControlToValidate="ddlSSiteClassification" InitialValue="0" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                </td>
                            </tr>
							<tr>
                                <td>
                                    Distance from coast
                                </td>
                                <td>
                                    <asp:TextBox ID="txtSDistFromCost" runat="server" class="Textbox" MaxLength="5" AutoCompleteType="Disabled"></asp:TextBox>
                                    <a href="#" class="bubble-popup-stl_dfc">?</a>
                                </td>
                                <td>
                                    <asp:RequiredFieldValidator ID="rfvSDistFromCost" ControlToValidate="txtSDistFromCost" runat="server" ErrorMessage="Required" 
                                        CssClass="Message NoMargin" ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True"></asp:RequiredFieldValidator>
                                    <asp:RegularExpressionValidator ID="regSDistFromCost" ControlToValidate="txtSDistFromCost" runat="server" ErrorMessage="Invalid input" CssClass="Message NoMargin" 
                                        ValidationGroup="Asset" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <asp:Button ID="btnAddSteelAsset" runat="server" class="Buttons" Text="Save" onclick="btnAddSteelAsset_Click" ToolTip="Add Asset to Inputs." ValidationGroup="Asset" />
                                    &nbsp;
                                    <input id="ipClearSteel" type="reset" class="Buttons" value="Clear" />
                                    <asp:Button ID="btnClearSteelAsset" ClientIDMode="Static" runat="server" Text="Clear Form" OnClick="btnClearSteelAsset_Click" style="display:none;"/>
                                </td>
                                <td>
                                </td>
                            </tr>   
                        </table>
                    </div>

                    <div id="divSpacer"></div>

                    <div id="divGridSteel" runat="server" clientidmode="Static">
                        <div>
                            <a href="#New" onclick="AddNewAssetSteel();">Add New Asset</a>                        
                        </div>

                        <div id="divSpacer"></div>       

                        <table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <th>Asset Code</th>
                                <th>Year built</th>
                                <th>Description</th>
								<th>Zone</th>
								<th>Hazard zone</th>
								<th>Coastal zone</th>
								<th>Coast class</th>
								<th>Site class</th>
								<th>Distance from coast</th>				
                                <th></th>				
                            </tr>     
                            <asp:ListView ID="lstAssetsSteel" runat="server" AllowPaging="false" AutoGenerateColumns="false" DataKeyNames="AssetCode" >
                                <ItemTemplate>
                                    <tr>
                                        <td><%# Eval("AssetCode") %></td>
                                        <td><%# Eval("YearBuilt")%></td>
                                        <td><%# Eval("Description") %></td>
										<td><%# Eval("Zone") %></td>
                                        <td><%# Eval("HazardZone") %></td>                
                                        <td><%# Eval("CoastalZone") %></td>
                                        <td><%# Eval("CoastClass") %></td>
                                        <td><%# Eval("SiteClass")%></td>
                                        <td><%# Eval("DistFromCost")%></td>             
                                        <td>
                                            <div id="divGridButtons">
                                                <asp:ImageButton ID="btnEditSteelAsset" ImageUrl="~/images/Article.png" runat="server" onclick="btnEditSteelAsset_Click" 
                                                    ToolTip="Edit Asset" CommandArgument='<%# Eval("AssetCode") %>'/>
                                                &nbsp;
                                                <asp:ImageButton ID="btnDeleteSteelAsset" ImageUrl="~/images/cmdDelete.png" runat="server" onclick="btnDeleteSteelAsset_Click" 
                                                    ToolTip="Delete Asset" CommandArgument='<%# Eval("AssetCode") %>' OnClientClick='return Confirm("Are you sure you want to delete this asset?", 0, this);'/>
                                            </div>                                      
                                        </td>
                                    </tr>
                                </ItemTemplate>
                            </asp:ListView>                            
                        </table>
                    </div>

                </div>                
				
				<div id="divSpacer"></div>

				<table cellpadding="0" cellspacing="0" width="100%">
			     	<tr>
			     		<td width="50%">
                            <asp:Button ID="lnkBack" runat="server" class="Buttons" Text="Back" OnClientClick="return GoBack()" ToolTip="Back to previous page." />
			     		</td>
			     		<td width="50%" align="right">
                            <asp:Button ID="btnNext" runat="server" Text="Run Model" onclick="btnNext_Click" OnClientClick="alert('Please wait while preparing your request.')" class="Buttons" ToolTip="Continue to next page."/>
			     		</td>
			     	</tr>
			    </table>		
			</div>
		</div>	
	</div>

    <script type="text/javascript">

        var _HideDetailConcrete = <%= Convert.ToString(HideDetailConcrete).ToLower() %>;
        var _HideDetailTimber = <%= Convert.ToString(HideDetailTimber).ToLower() %>;
        var _HideDetailSteel = <%= Convert.ToString(HideDetailSteel).ToLower() %>;

        function GoBack() 
        {
            location.href = 'materialproperties.aspx';
            return false;
        }

        function AddNewAssetConcrete()
        {
            if(document.getElementById("<%= txtFTAssetCode.ClientID %>").readOnly)
            {
                confirm("Are you sure you want to discard this data?", 0, document.getElementById("btnClearConcreteAsset"));
            }
            else
            {
                document.getElementById('ipClearConcrete').click();
                $('#divDetailConcrete').fadeIn('slow', null);
            }
        }
        
        function AddNewAssetTimber()
        {
            if(document.getElementById("<%= txtTAssetCode.ClientID %>").readOnly)
            {
                confirm("Are you sure you want to discard this data?", 0, document.getElementById("btnClearTimberAsset"));
            }
            else
            {
                document.getElementById('ipClearTimber').click();
                $('#divDetailTimber').fadeIn('slow', null);
            }
        }
        
        function AddNewAssetSteel()
        {
            if(document.getElementById("<%= txtSAssetCode.ClientID %>").readOnly)
            {
                confirm("Are you sure you want to discard this data?", 0, document.getElementById("btnClearSteelAsset"));
            }
            else
            {
                document.getElementById('ipClearSteel').click();
                $('#divDetailSteel').fadeIn('slow', null);
            }
        }
        
        if(_HideDetailConcrete)
            $('#divDetailConcrete').fadeOut('fast', null);
        if(_HideDetailTimber)
            $('#divDetailTimber').fadeOut('fast', null);
        if(_HideDetailSteel)
            $('#divDetailSteel').fadeOut('fast', null);
    </script>

    <asp:UpdatePanel id="updUpload" runat="server" UpdateMode="Conditional">
    <ContentTemplate>
            
            <asp:Panel ID="divUpload" runat="server" Style="display: none;" ClientIDMode="Static">                
                <div id="UploadHeading">                    
                    <input type="button" value="X" title="Close" id="cmdClose" />
                    <div id="divHeadingText">
                        <h4>
                            <u>Upload File</u>
                        </h4> 
                    </div>     
                </div>
                <div id="divUploadContent" align="center">
                    <input type="file" id="FileUpload" runat="server" onchange="Upload(this)" />
                    <br />
                    <asp:Label ID="lblUploadMsg" runat="server" ClientIDMode="Static"></asp:Label>
                </div>                
            </asp:Panel>

            <asp:Button ID="cmdShow" runat="server" style="display:none;" />
            <asp:Button ID="cmdUpload" runat="server" style="display:none;" onclick="btnUploadInputData_Click"  />

            <cc1:ModalPopupExtender ID="pceRecovery" runat="server" PopupControlID="divUpload"
                TargetControlID="cmdShow" BackgroundCssClass="modalBackground" CancelControlID="cmdClose"
                PopupDragHandleControlID="UploadHeading" DropShadow="true" CacheDynamicResults="true"
                EnableViewState="true" RepositionMode="RepositionOnWindowResizeAndScroll" OnCancelScript="Reset()">
            </cc1:ModalPopupExtender>

            <script type="text/javascript" language="javascript">
                var Extentions = new Array();
                <%= GetExtentions() %>

                function Display() {
                    document.getElementById("pnlPopUp").style.display = "block";
                }

                function Reset() {
                    document.getElementById("<%= lblUploadMsg.ClientID %>").innerHTML = "";
                }

                function ShowUpload() {
                    document.getElementById("<%= cmdShow.ClientID %>").click();
                }

                function Upload(control) {
                    var Lbl = document.getElementById("<%= lblUploadMsg.ClientID %>");

                    if (control.value == "") {
                        Lbl.innerHTML = "Please select a file first.";
                        return;
                    }

                    var FileName = control.value.split('.');
                    var IsValid = false;

                    for (var i = 0; i < Extentions.length; i++) {
                        if (Extentions[i].toLowerCase() == FileName[FileName.length - 1].toLowerCase())
                            IsValid = true;
                    }

                    if (!IsValid) {
                        Lbl.innerHTML = "Invalid file type. Please upload a file with <%= _Extentions %> extentions.";
                        return;
                    }

                    document.getElementById("<%= cmdUpload.ClientID %>").click();
                }
    </script>

    </ContentTemplate>
    <Triggers>
        <asp:PostbackTrigger ControlID="cmdUpload"></asp:PostbackTrigger>
    </Triggers>
</asp:UpdatePanel>

</asp:Content>
