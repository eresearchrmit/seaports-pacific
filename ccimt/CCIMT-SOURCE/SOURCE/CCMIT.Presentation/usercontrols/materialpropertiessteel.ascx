<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="materialpropertiessteel.ascx.cs" Inherits="CCIMT.Presentation.usercontrols.materialpropertiessteel" %>
<hr />
<div id="divHeader2Alt">
	Material: Steel
</div>
		
<div id="divSpacer"></div>
       		     
<asp:UpdatePanel ID="panelFirstTable" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

	    <table cellpadding="0" cellspacing="0">
			<tr>
				<th>Class</th>
				<th>Category</th>
				<th>Mean<br />(&mu;m/yr)</th>
                <th>Typical exterior environment</th>
			</tr>
            <tr>
				<td>C1</td>
				<td>Very Low</td>
				<td>&lt;1.3</td>
                <td>Few alpine areas</td>
			</tr>
            <tr>
				<td>C2</td>
				<td>Low</td>
				<td>1.3 - 25</td>
                <td>Arid/rural/urban</td>
			</tr>
            <tr>
				<td>C3</td>
				<td>Medium</td>
				<td>25 - 50</td>
                <td>Coastal</td>
			</tr>
            <tr>
				<td>C4</td>
				<td>High</td>
				<td>50 - 80</td>
                <td>Seashore (calm)</td>
			</tr>
            <tr>
				<td>C5</td>
				<td>Very High</td>
				<td>80 - 190</td>
                <td>Seashore (surf)/offshore</td>
			</tr>
		</table>
		*AS2312/ISO9223			
    </ContentTemplate>
</asp:UpdatePanel>
                        
<div id="divSpacer"></div>
				     
<asp:UpdatePanel ID="panelNOfRD" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

		<h3>Number of rain days</h3>
		<hr />
					
		<table cellpadding="0" cellspacing="0">
			<tr>
				<th>Zone</th>
				<th>Coastal zone</th>
				<th>D<sub>rain</sub></th>
			</tr>
            <tr>
				<td>A</td>
				<td>C1</td>
				<td>
                    <asp:Label ID="lblNORDA" runat="server"></asp:Label>
                    <asp:TextBox ID="txtNORDA" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvNORDA" ControlToValidate="txtNORDA" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="NORD" SetFocusOnError="True"></asp:RequiredFieldValidator> 
                    <asp:RegularExpressionValidator ID="regNORDA" ControlToValidate="txtNORDA" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="NORD" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$" >
                    </asp:RegularExpressionValidator>                   
                </td>
			</tr>
			<tr>
				<td>B</td>
				<td>C2</td>
				<td>
                    <asp:Label ID="lblNORDB" runat="server"></asp:Label>
                    <asp:TextBox ID="txtNORDB" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvNORDB" ControlToValidate="txtNORDB" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="NORD" SetFocusOnError="True"></asp:RequiredFieldValidator> 
                    <asp:RegularExpressionValidator ID="regNORDB" ControlToValidate="txtNORDB" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="NORD" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$" >
                    </asp:RegularExpressionValidator>                   
                </td>
			</tr>	
            <tr>
				<td>C</td>
				<td>C1</td>
				<td>
                    <asp:Label ID="lblNORDC" runat="server"></asp:Label>
                    <asp:TextBox ID="txtNORDC" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvNORDC" ControlToValidate="txtNORDC" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="NORD" SetFocusOnError="True"></asp:RequiredFieldValidator> 
                    <asp:RegularExpressionValidator ID="regNORDC" ControlToValidate="txtNORDC" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="NORD" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$" >
                    </asp:RegularExpressionValidator>                   
                </td>
			</tr>	
            <tr>
				<td>D</td>
				<td>C2</td>
				<td>
                    <asp:Label ID="lblNORDD" runat="server"></asp:Label>
                    <asp:TextBox ID="txtNORDD" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvNORDD" ControlToValidate="txtNORDD" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="NORD" SetFocusOnError="True"></asp:RequiredFieldValidator> 
                    <asp:RegularExpressionValidator ID="regNORDD" ControlToValidate="txtNORDD" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="NORD" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$" >
                    </asp:RegularExpressionValidator>                   
                </td>
			</tr>	
            <tr>
				<td>E</td>
				<td>C3</td>
				<td>
                    <asp:Label ID="lblNORDE" runat="server"></asp:Label>
                    <asp:TextBox ID="txtNORDE" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvNORDE" ControlToValidate="txtNORDE" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="NORD" SetFocusOnError="True"></asp:RequiredFieldValidator> 
                    <asp:RegularExpressionValidator ID="regNORDE" ControlToValidate="txtNORDE" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="NORD" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$" >
                    </asp:RegularExpressionValidator>                   
                </td>
			</tr>
		</table>
		<div id="divButtons">
            <asp:Button ID="btnNORDEdit" runat="server" Text="Edit" onclick="btnNORDEdit_Click" />
            <asp:Button ID="btnNORDCancel" runat="server" Text="Cancel" Visible="false" onclick="btnNORDCancel_Click" />                                        
		</div>
					
    </ContentTemplate>
</asp:UpdatePanel>
                        
<div id="divSpacer"></div>
					
<asp:UpdatePanel ID="panelZF" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

		<h3>Zone factors</h3>
		<hr />
					
		<table cellpadding="0" cellspacing="0">
			<tr>
				<th>Coastal zone</th>
				<th>&alpha;<sub>coast</sub></th>
				<th>&alpha;<sub>ocean</sub></th>
			</tr>
            <tr>
				<td>C1</td>
				<td>
                    <asp:Label ID="lblZFAC1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtZFAC1" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvZFAC1" ControlToValidate="txtZFAC1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ZF" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revZFAC1" ControlToValidate="txtZFAC1" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="ZF" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$" >
                    </asp:RegularExpressionValidator>
                </td>
				<td>
                    <asp:Label ID="lblZFAO1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtZFAO1" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvZFAO1" ControlToValidate="txtZFAO1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ZF" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revZFAO1" ControlToValidate="txtZFAO1" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="ZF" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$" >
                    </asp:RegularExpressionValidator>
                </td>
			</tr>
			<tr>
				<td>C2</td>
				<td>
                    <asp:Label ID="lblZFAC2" runat="server"></asp:Label>
                    <asp:TextBox ID="txtZFAC2" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvZFAC2" ControlToValidate="txtZFAC2" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ZF" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revZFAC2" ControlToValidate="txtZFAC2" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="ZF" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$" >
                    </asp:RegularExpressionValidator>
                </td>
				<td>
                    <asp:Label ID="lblZFAO2" runat="server"></asp:Label>
                    <asp:TextBox ID="txtZFAO2" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvZFAO2" ControlToValidate="txtZFAO2" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ZF" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revZFAO2" ControlToValidate="txtZFAO2" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="ZF" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$" >
                    </asp:RegularExpressionValidator>
                </td>
			</tr>
            <tr>
				<td>C3</td>
				<td>
                    <asp:Label ID="lblZFAC3" runat="server"></asp:Label>
                    <asp:TextBox ID="txtZFAC3" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvZFAC3" ControlToValidate="txtZFAC3" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ZF" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revZFAC3" ControlToValidate="txtZFAC3" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="ZF" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$" >
                    </asp:RegularExpressionValidator>
                </td>
				<td>
                    <asp:Label ID="lblZFAO3" runat="server"></asp:Label>
                    <asp:TextBox ID="txtZFAO3" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvZFAO3" ControlToValidate="txtZFAO3" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ZF" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revZFAO3" ControlToValidate="txtZFAO3" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="ZF" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^0*[1-9][0-9]*$" >
                    </asp:RegularExpressionValidator>
                </td>
			</tr>
		</table>
		<div id="divButtons">
            <asp:Button ID="btnZFEdit" runat="server" Text="Edit" onclick="btnZFEdit_Click" />
            <asp:Button ID="btnZFCancel" runat="server" Text="Cancel" Visible="false" onclick="btnZFCancel_Click" />                                        
		</div>
					
    </ContentTemplate>
</asp:UpdatePanel>
                        
<div id="divSpacer"></div>

<asp:UpdatePanel ID="panelCE" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

		<h3>Coastal exposure</h3>
		<hr />
					
		<table cellpadding="0" cellspacing="0">
			<tr>
				<th>Class</th>
				<th>Coastal exposure condition</th>
				<th>&beta;<sub>coast</sub></th>
			</tr>
            <tr>
				<td>EXP1</td>
				<td>Closed bay</td>
				<td>
                    <asp:Label ID="lblCEBCExp1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCEBCExp1" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCEBCExp1" ControlToValidate="txtCEBCExp1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CE" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revCEBCExp1" ControlToValidate="txtCEBCExp1" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="CE" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
            </tr>
            <tr>
				<td>EXP2</td>
				<td>Partially closed bay</td>
				<td>
                    <asp:Label ID="lblCEBCExp2" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCEBCExp2" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCEBCExp2" ControlToValidate="txtCEBCExp2" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CE" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revCEBCExp2" ControlToValidate="txtCEBCExp2" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="CE" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
            </tr>
            <tr>
				<td>EXP3</td>
				<td>Open bay</td>
				<td>
                    <asp:Label ID="lblCEBCExp3" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCEBCExp3" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCEBCExp3" ControlToValidate="txtCEBCExp3" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CE" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revCEBCExp3" ControlToValidate="txtCEBCExp3" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="CE" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
            </tr>
            <tr>
				<td>EXP4</td>
				<td>Open surf</td>
				<td>
                    <asp:Label ID="lblCEBCExp4" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCEBCExp4" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCEBCExp4" ControlToValidate="txtCEBCExp4" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CE" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revCEBCExp4" ControlToValidate="txtCEBCExp4" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="CE" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
            </tr>
        </table>
		<div id="divButtons">
            <asp:Button ID="btnCEEdit" runat="server" Text="Edit" onclick="btnCEEdit_Click" />
            <asp:Button ID="btnCECancel" runat="server" Text="Cancel" Visible="false" onclick="btnCECancel_Click" />                                        
		</div>
					
    </ContentTemplate>
</asp:UpdatePanel>

<div id="divSpacer"></div>

<asp:UpdatePanel ID="panelSC" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

		<h3>Site classification</h3>
		<hr />
					
		<table cellpadding="0" cellspacing="0">
			<tr>
				<th>Class</th>
				<th>Site classification</th>
				<th>&alpha;<sub>exo</sub></th>
			</tr>
            <tr>
				<td>SITE1</td>
				<td>Open to sea</td>
				<td>
                    <asp:Label ID="lblSCSite1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtSCSite1" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvSCSite1" ControlToValidate="txtSCSite1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="SC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revSCSite1" ControlToValidate="txtSCSite1" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="SC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
            </tr>
            <tr>
				<td>SITE2</td>
				<td>Urban (suburbs)</td>
				<td>
                    <asp:Label ID="lblSCSite2" runat="server"></asp:Label>
                    <asp:TextBox ID="txtSCSite2" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvSCSite2" ControlToValidate="txtSCSite2" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="SC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revSCSite2" ControlToValidate="txtSCSite2" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="SC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
            </tr>
            <tr>
				<td>SITE3</td>
				<td>Urban (city centre)</td>
				<td>
                    <asp:Label ID="lblSCSite3" runat="server"></asp:Label>
                    <asp:TextBox ID="txtSCSite3" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvSCSite3" ControlToValidate="txtSCSite3" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="SC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revSCSite3" ControlToValidate="txtSCSite3" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="SC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
            </tr>
            <tr>
				<td>SITE4</td>
				<td>Other sites</td>
				<td>
                    <asp:Label ID="lblSCSite4" runat="server"></asp:Label>
                    <asp:TextBox ID="txtSCSite4" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvSCSite4" ControlToValidate="txtSCSite4" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="SC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revSCSite4" ControlToValidate="txtSCSite4" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="SC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
            </tr>
        </table>
		<div id="divButtons">
            <asp:Button ID="btnSCEdit" runat="server" Text="Edit" onclick="btnSCEdit_Click" />
            <asp:Button ID="btnSCCancel" runat="server" Text="Cancel" Visible="false" onclick="btnSCCancel_Click" />                                        
		</div>
					
    </ContentTemplate>
</asp:UpdatePanel>

<div id="divSpacer"></div>

<table id="tblUploadControl" cellpadding="0" cellspacing="0">
	<tr>
		<td>
            <asp:Button ID="btnSteelBack" runat="server" class="Buttons" Text="Back" OnClientClick="return GoBack()" ToolTip="Back to previous page." />
		</td>
		<td align="right">
			<asp:Button ID="btnSteelNext" runat="server" Text="Next" onclick="btnNext_Click" class="Buttons" ToolTip="Continue to next page."/>
		</td>
	</tr>
</table>