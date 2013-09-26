<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="materialpropertiesconcrete.ascx.cs" Inherits="CCIMT.Presentation.usercontrols.materialpropertiesconcrete" %>
<hr />
<div id="divHeader2Alt">
	Material: Concrete
</div>
				     
<asp:UpdatePanel ID="panelCCR" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

		<h3>Carbonation corrosion rates at 20°C</h3>
		<hr />
					
		<table cellpadding="0" cellspacing="0">
			<tr>
				<th>Class</th>
				<th>Description</th>
				<th>Mean<br/>(µA/cm<sup>2</sup>)</th>
				<th>SD<br/>(µA/cm<sup>2</sup>)</th>
				<th>Distribution</th>
			</tr>
			<tr>
				<td>CB1</td>
				<td>Dry</td>
				<td>
                    <asp:Label ID="lblCB1Mean" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCB1Mean" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCB1Mean" ControlToValidate="txtCB1Mean" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CarbCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
				<td>
                    <asp:Label ID="lblCB1SD" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCB1SD" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCB1SD" ControlToValidate="txtCB1SD" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CarbCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
				<td>lognormal</td>
			</tr>
			<tr>
				<td>CB2</td>
				<td>Wet-rarely dry (unsheltered)</td>
				<td>
                    <asp:Label ID="lblCB2Mean" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCB2Mean" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCB2Mean" ControlToValidate="txtCB2Mean" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CarbCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
				<td>
                    <asp:Label ID="lblCB2SD" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCB2SD" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCB2SD" ControlToValidate="txtCB2SD" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CarbCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
				<td>lognormal</td>
			</tr>
			<tr>
				<td>CB3</td>
				<td>Moderate humidity (sheltered)</td>
				<td>
                    <asp:Label ID="lblCB3Mean" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCB3Mean" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCB3Mean" ControlToValidate="txtCB3Mean" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CarbCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
				<td>
                    <asp:Label ID="lblCB3SD" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCB3SD" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCB3SD" ControlToValidate="txtCB3SD" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CarbCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
				<td>lognormal</td>
			</tr>
			<tr>
				<td>CB4</td>
				<td>Cyclic wet-dry (unsheltered)</td>
				<td>
                    <asp:Label ID="lblCB4Mean" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCB4Mean" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCB4Mean" ControlToValidate="txtCB4Mean" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CarbCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
				<td>
                    <asp:Label ID="lblCB4SD" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCB4SD" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCB4SD" ControlToValidate="txtCB4SD" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CarbCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
				<td>lognormal</td>
			</tr>
		</table>
		<div id="divButtons">
            <asp:Button ID="btnCCREdit" runat="server" Text="Edit" onclick="btnCCREdit_Click" />
            <asp:Button ID="btnCCRCancel" runat="server" Text="Cancel" Visible="false" onclick="btnCCRCancel_Click" />

            <asp:RegularExpressionValidator ID="regCB1Mean" ControlToValidate="txtCB1Mean" runat="server" ErrorMessage="CB1 mean value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="CarbCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" ></asp:RegularExpressionValidator> 
            &nbsp;
            <asp:RegularExpressionValidator ID="regCB1SD" ControlToValidate="txtCB1SD" runat="server" ErrorMessage="CB1 SD value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="CarbCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$"></asp:RegularExpressionValidator>             
            &nbsp;
            <asp:RegularExpressionValidator ID="regCB2Mean" ControlToValidate="txtCB2Mean" runat="server" ErrorMessage="CB2 mean value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="CarbCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$"></asp:RegularExpressionValidator> 
            &nbsp;
            <asp:RegularExpressionValidator ID="regCB2SD" ControlToValidate="txtCB2SD" runat="server" ErrorMessage="CB2 SD value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="CarbCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$"></asp:RegularExpressionValidator>                                
            &nbsp;
            <asp:RegularExpressionValidator ID="regCB3Mean" ControlToValidate="txtCB3Mean" runat="server" ErrorMessage="CB3 mean value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="CarbCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$"></asp:RegularExpressionValidator> 
            &nbsp;
            <asp:RegularExpressionValidator ID="regCB3SD" ControlToValidate="txtCB3SD" runat="server" ErrorMessage="CB3 SD value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="CarbCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$"></asp:RegularExpressionValidator>                                
            &nbsp;
            <asp:RegularExpressionValidator ID="regCB4Mean" ControlToValidate="txtCB4Mean" runat="server" ErrorMessage="CB4 mean value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="CarbCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$"></asp:RegularExpressionValidator> 
            &nbsp;
            <asp:RegularExpressionValidator ID="regCB4SD" ControlToValidate="txtCB4SD" runat="server" ErrorMessage="CB4 SD value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="CarbCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$"></asp:RegularExpressionValidator> 
                                                       
		</div>
					
    </ContentTemplate>
</asp:UpdatePanel>
                        
<div id="divSpacer"></div>
					
<asp:UpdatePanel ID="panelChCR" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

		<h3>Chloride corrosion rates at 20°C</h3>
		<hr />
					
		<table cellpadding="0" cellspacing="0">
			<tr>
				<th>Class</th>
				<th>Description</th>
				<th>Mean<br>(µA/cm<sup>2</sup>)</th>
				<th>SD<br>(µA/cm<sup>2</sup>)</th>
				<th>Distribution</th>
			</tr>
			<tr>
				<td>CL1</td>
				<td>Wet-rarely dry</td>
                <td>
                    <asp:Label ID="lblCL1Mean" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCL1Mean" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCL1Mean" ControlToValidate="txtCL1Mean" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ChlCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                     
                </td>
                <td>
                    <asp:Label ID="lblCL1SD" runat="server">
                    </asp:Label><asp:TextBox ID="txtCL1SD" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCL1SD" ControlToValidate="txtCL1SD" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ChlCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
				<td style="width: 106px">lognormal</td>
			</tr>
			<tr>
				<td>CL2</td>
				<td>Cyclic wet-dry</td>
				<td>
                    <asp:Label ID="lblCL2Mean" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCL2Mean" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCL2Mean" ControlToValidate="txtCL2Mean" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ChlCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
                <td>
                    <asp:Label ID="lblCL2SD" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCL2SD" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCL2SD" ControlToValidate="txtCL2SD" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ChlCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
				<td>lognormal</td>
			</tr>
			<tr>
				<td>CL3</td>
				<td>Airborne sea water</td>
				<td>
                    <asp:Label ID="lblCL3Mean" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCL3Mean" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCL3Mean" ControlToValidate="txtCL3Mean" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ChlCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
                <td>
                    <asp:Label ID="lblCL3SD" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCL3SD" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCL3SD" ControlToValidate="txtCL3SD" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ChlCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
				<td>lognormal</td>
			</tr>
			<tr>
				<td>CL4</td>
				<td>Submerged</td>
				<td>
                    <asp:Label ID="lblCL4Mean" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCL4Mean" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCL4Mean" ControlToValidate="txtCL4Mean" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ChlCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                     
                </td>
                <td>
                    <asp:Label ID="lblCL4SD" runat="server">
                    </asp:Label><asp:TextBox ID="txtCL4SD" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCL4SD" ControlToValidate="txtCL4SD" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ChlCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
				<td style="width: 106px">lognormal</td>
			</tr>
			<tr>
				<td>CL5</td>
				<td>Tidal zone</td>
				<td>
                    <asp:Label ID="lblCL5Mean" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCL5Mean" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCL5Mean" ControlToValidate="txtCL5Mean" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ChlCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
                <td>
                    <asp:Label ID="lblCL5SD" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCL5SD" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCL5SD" ControlToValidate="txtCL5SD" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="ChlCR20" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    
                </td>
				<td style="width: 106px">lognormal</td>
			</tr>
		</table>
					
		<div id="divButtons">
			<asp:Button ID="btnChCREdit" runat="server" Text="Edit" onclick="btnChCREdit_Click" />
            <asp:Button ID="btnChCRCancel" runat="server" Text="Cancel" Visible="false" onclick="btnChCRCancel_Click" />
            <asp:RegularExpressionValidator ID="regCL1Mean" ControlToValidate="txtCL1Mean" runat="server" ErrorMessage="CL1 mean value is incorrect." CssClass="Message NoMargin" 
            ValidationGroup="ChlCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator>
            &nbsp;
            <asp:RegularExpressionValidator ID="regCL1SD" ControlToValidate="txtCL1SD" runat="server" ErrorMessage="CL1 SD value is incorrect." CssClass="Message NoMargin" 
            ValidationGroup="ChlCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator>
            &nbsp;
            <asp:RegularExpressionValidator ID="regCL2Mean" ControlToValidate="txtCL2Mean" runat="server" ErrorMessage="CL2 mean value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="ChlCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator> 
            &nbsp;
            <asp:RegularExpressionValidator ID="regCL2SD" ControlToValidate="txtCL2SD" runat="server" ErrorMessage="CL2 SD value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="ChlCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator>
            &nbsp;
            <asp:RegularExpressionValidator ID="regCL3Mean" ControlToValidate="txtCL3Mean" runat="server" ErrorMessage="CL3 mean value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="ChlCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator> 
            &nbsp;
            <asp:RegularExpressionValidator ID="regCL3SD" ControlToValidate="txtCL3SD" runat="server" ErrorMessage="CL3 SD value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="ChlCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator>
            &nbsp;
            <asp:RegularExpressionValidator ID="regCL4Mean" ControlToValidate="txtCL4Mean" runat="server" ErrorMessage="CL4 mean value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="ChlCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator>
            &nbsp;
            <asp:RegularExpressionValidator ID="regCL4SD" ControlToValidate="txtCL4SD" runat="server" ErrorMessage="CL4 SD value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="ChlCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator>
            &nbsp;
            <asp:RegularExpressionValidator ID="regCL5Mean" ControlToValidate="txtCL5Mean" runat="server" ErrorMessage="CL5 mean value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="ChlCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator> 
            &nbsp;
            <asp:RegularExpressionValidator ID="regCL5SD" ControlToValidate="txtCL5SD" runat="server" ErrorMessage="CL5 SD value is incorrect." CssClass="Message NoMargin" 
                        ValidationGroup="ChlCR20" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator>
		</div>

    </ContentTemplate>
</asp:UpdatePanel>

<div id="divSpacer"></div>

<asp:UpdatePanel ID="panelCO2DC" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

		<h3>CO<sub>2</sub> diffusion coefficient</h3>
		<hr />
					
		<table cellpadding="0" cellspacing="0">
			<tr>
				<th>w/c</th>
				<th>D<sub>1</sub>×10<sup>-4</sup><br>(cm<sup>2</sup>/s)</th>
				<th>n<sub>d</sub></th>
			</tr>
			<tr>
				<td>0.30</td>
                <td>
                    <asp:Label ID="lblCO2DC30D1" runat="server">
                    </asp:Label><asp:TextBox ID="txtCO2DC30D1" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC30D1" ControlToValidate="txtCO2DC30D1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC30D1" ControlToValidate="txtCO2DC30D1" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>
                <td>
                    <asp:Label ID="lblCO2DC30Nd" runat="server">
                    </asp:Label><asp:TextBox ID="txtCO2DC30Nd" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC30Nd" ControlToValidate="txtCO2DC30Nd" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC30Nd" ControlToValidate="txtCO2DC30Nd" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>							
			</tr>
			<tr>
				<td>0.35</td>							
                <td>
                    <asp:Label ID="lblCO2DC35D1" runat="server">
                    </asp:Label><asp:TextBox ID="txtCO2DC35D1" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC35D1" ControlToValidate="txtCO2DC35D1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC35D1" ControlToValidate="txtCO2DC35D1" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>
                <td>
                    <asp:Label ID="lblCO2DC35Nd" runat="server">
                    </asp:Label><asp:TextBox ID="txtCO2DC35Nd" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC35Nd" ControlToValidate="txtCO2DC35Nd" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC35Nd" ControlToValidate="txtCO2DC35Nd" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>
			</tr>
			<tr>
				<td>0.40</td>
                <td>
                    <asp:Label ID="lblCO2DC40D1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCO2DC40D1" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC40D1" ControlToValidate="txtCO2DC40D1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC40D1" ControlToValidate="txtCO2DC40D1" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>
                <td>
                    <asp:Label ID="lblCO2DC40Nd" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCO2DC40Nd" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC40Nd" ControlToValidate="txtCO2DC40Nd" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC40Nd" ControlToValidate="txtCO2DC40Nd" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>		
			</tr>
			<tr>
				<td>0.45</td>
                <td>
                    <asp:Label ID="lblCO2DC45D1" runat="server">
                    </asp:Label><asp:TextBox ID="txtCO2DC45D1" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC45D1" ControlToValidate="txtCO2DC45D1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC45D1" ControlToValidate="txtCO2DC45D1" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>
                <td>
                    <asp:Label ID="lblCO2DC45Nd" runat="server">
                    </asp:Label><asp:TextBox ID="txtCO2DC45Nd" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC45Nd" ControlToValidate="txtCO2DC45Nd" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC45Nd" ControlToValidate="txtCO2DC45Nd" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>			
			</tr>
			<tr>
				<td>0.50</td>
                <td>
                    <asp:Label ID="lblCO2DC50D1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCO2DC50D1" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC50D1" ControlToValidate="txtCO2DC50D1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC50D1" ControlToValidate="txtCO2DC50D1" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>
                <td>
                    <asp:Label ID="lblCO2DC50Nd" runat="server">
                    </asp:Label><asp:TextBox ID="txtCO2DC50Nd" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC50Nd" ControlToValidate="txtCO2DC50Nd" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC50Nd" ControlToValidate="txtCO2DC50Nd" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>			
			</tr>
			<tr>
				<td>0.55</td>
                <td>
                    <asp:Label ID="lblCO2DC55D1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCO2DC55D1" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC55D1" ControlToValidate="txtCO2DC55D1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC55D1" ControlToValidate="txtCO2DC55D1" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>    
                </td>
                <td>
                    <asp:Label ID="lblCO2DC55Nd" runat="server">
                    </asp:Label><asp:TextBox ID="txtCO2DC55Nd" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC55Nd" ControlToValidate="txtCO2DC55Nd" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC55Nd" ControlToValidate="txtCO2DC55Nd" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>			
			</tr>
			<tr>
				<td>0.60</td>
                <td>
                    <asp:Label ID="lblCO2DC60D1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCO2DC60D1" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC60D1" ControlToValidate="txtCO2DC60D1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC60D1" ControlToValidate="txtCO2DC60D1" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>
                <td>
                    <asp:Label ID="lblCO2DC60Nd" runat="server">
                    </asp:Label><asp:TextBox ID="txtCO2DC60Nd" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC60Nd" ControlToValidate="txtCO2DC60Nd" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC60Nd" ControlToValidate="txtCO2DC60Nd" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>			
			</tr>
			<tr>
				<td>0.65</td>
                <td>
                    <asp:Label ID="lblCO2DC65D1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCO2DC65D1" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC65D1" ControlToValidate="txtCO2DC65D1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC65D1" ControlToValidate="txtCO2DC65D1" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>
                <td>
                    <asp:Label ID="lblCO2DC65Nd" runat="server">
                    </asp:Label><asp:TextBox ID="txtCO2DC65Nd" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCO2DC65Nd" ControlToValidate="txtCO2DC65Nd" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CO2DC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCO2DC65Nd" ControlToValidate="txtCO2DC65Nd" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CO2DC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,5}$"></asp:RegularExpressionValidator>
                </td>			
			</tr>
		</table>
					
		<div id="divButtons">
			<asp:Button ID="btnCO2DCEdit" runat="server" Text="Edit" onclick="btnCO2DCEdit_Click" />
            <asp:Button ID="btnCO2DCCancel" runat="server" Text="Cancel" Visible="false" onclick="btnCO2DCCancel_Click" />
		</div>
    </ContentTemplate>
</asp:UpdatePanel>

<div id="divSpacer"></div>

<asp:UpdatePanel ID="panelActEnergy" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

		<h3>Activation energy</h3>
		<hr />
					
		<table cellspacing="0" cellpadding="0">
			<tr>
				<th>w/c</th>
				<th>E<br>(kJ/mol)</th>
			</tr>
			<tr>
				<td>0.3</td>
				<td>
                    <asp:Label ID="lblAE30Energy" runat="server">
                    </asp:Label><asp:TextBox ID="txtAE30Energy" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvAE30Energy" ControlToValidate="txtAE30Energy" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="AE" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regAE30Energy" ControlToValidate="txtAE30Energy" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="AE" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>
                </td>
			</tr>
			<tr>
				<td>0.4</td>
				<td>
                    <asp:Label ID="lblAE40Energy" runat="server">
                    </asp:Label><asp:TextBox ID="txtAE40Energy" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvAE40Energy" ControlToValidate="txtAE40Energy" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="AE" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regAE40Energy" ControlToValidate="txtAE40Energy" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="AE" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>
                </td>
			</tr>
			<tr>
				<td>0.5</td>
				<td>
                    <asp:Label ID="lblAE50Energy" runat="server">
                    </asp:Label><asp:TextBox ID="txtAE50Energy" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvAE50Energy" ControlToValidate="txtAE50Energy" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="AE" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regAE50Energy" ControlToValidate="txtAE50Energy" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="AE" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>
                </td>
			</tr>
			<tr>
				<td>0.6</td>
				<td>
                    <asp:Label ID="lblAE60Energy" runat="server">
                    </asp:Label><asp:TextBox ID="txtAE60Energy" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvAE60Energy" ControlToValidate="txtAE60Energy" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="AE" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regAE60Energy" ControlToValidate="txtAE60Energy" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="AE" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>
                </td>
			</tr>
			<tr>
				<td>0.7</td>
				<td>
                    <asp:Label ID="lblAE70Energy" runat="server">
                    </asp:Label><asp:TextBox ID="txtAE70Energy" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvAE70Energy" ControlToValidate="txtAE70Energy" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="AE" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regAE70Energy" ControlToValidate="txtAE70Energy" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="AE" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>
                </td>
			</tr>
		</table>					        
		Range &nbsp;&nbsp;32-50kJ/mol
		<br/>
					
		<div id="divButtons">
			<asp:Button ID="btnAEEdit" runat="server" Text="Edit" onclick="btnAEEdit_Click" />
            <asp:Button ID="btnAECancel" runat="server" Text="Cancel" Visible="false" onclick="btnAECancel_Click" />
		</div>
    </ContentTemplate>
</asp:UpdatePanel>                                
                                	
<div id="divSpacer"></div>

<asp:UpdatePanel ID="panelEnvironment" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

        <h3>Surface Chloride Concentration</h3>
		<hr />

		<table cellpadding="0" cellspacing="0">
			<tr>
				<th>Environment</th>
				<%--<th>Type</th>--%>
				<th>Mean<br />(kg/m<sup>3</sup>)</th>			
			</tr>
			<tr>
				<td>tidal/splash</td>
                <%--<td><asp:TextBox ID="txtEnv1Type" class="Textbox Mini" runat="server"></asp:TextBox></td>--%>
                <td>
                    <asp:Label ID="lblEnv1TidSplMean" runat="server">
                    </asp:Label><asp:TextBox ID="txtEnv1TidSplMean" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvEnv1TidSplMean" ControlToValidate="txtEnv1TidSplMean" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="SCC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regEnv1TidSplMean" ControlToValidate="txtEnv1TidSplMean" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="SCC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator>                                                                
                </td>							
			</tr>
			<tr>
				<td>atmospheric<br />(coast)</td>
				<%--<td><asp:TextBox ID="txtEnv2Type" class="Textbox Mini" runat="server"></asp:TextBox></td>--%>
                <td>
                    <asp:Label ID="lblEnv2AtmNearMean" runat="server">
                    </asp:Label><asp:TextBox ID="txtEnv2AtmNearMean" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvEnv2AtmNearMean" ControlToValidate="txtEnv2AtmNearMean" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="SCC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regEnv2AtmNearMean" ControlToValidate="txtEnv2AtmNearMean" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="SCC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator>                                
                </td>									
			</tr>
			<tr>
				<td>atmospheric<br />(>1km fr coast)</td>
				<%--<td><asp:TextBox ID="txtEnv3Type" class="Textbox Mini" runat="server"></asp:TextBox></td>--%>
                <td>
                    <asp:Label ID="lblEnv3AtmFarMean" runat="server">
                    </asp:Label><asp:TextBox ID="txtEnv3AtmFarMean" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvEnv3AtmFarMean" ControlToValidate="txtEnv3AtmFarMean" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="SCC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regEnv3AtmFarMean" ControlToValidate="txtEnv3AtmFarMean" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="SCC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator>                                
                </td>							
			</tr>
		</table>
					
		<div id="divButtons">
			<asp:Button ID="btnEnvironmentEdit" runat="server" Text="Edit" onclick="btnEnvironmentEdit_Click" />
            <asp:Button ID="btnEnvironmentCancel" runat="server" Text="Cancel" Visible="false" onclick="btnEnvironmentCancel_Click" />
		</div>
                    	
    </ContentTemplate>
</asp:UpdatePanel>
                        				
<div id="divSpacer"></div>

<asp:UpdatePanel ID="panelCDC" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

		<h3>Chloride diffusion coefficient</h3>
		<hr />
					
		<table cellpadding="0" cellspacing="0">
			<tr>
				<th>w/c</th>
				<th>f'c<br>(MPa)</th>
				<th>D<sub>c</sub>×10<sup>-12</sup><br>(m<sup>2</sup>/s)</th>			
			</tr>
			<tr>
				<td>0.40</td>
                <td>
                    <asp:Label ID="lblCDC40Fc" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCDC40Fc" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCDC40Fc" ControlToValidate="txtCDC40Fc" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CDC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCDC40Fc" ControlToValidate="txtCDC40Fc" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CDC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>   
                </td>
                <td>
                    <asp:Label ID="lblCDC40Dc" runat="server"></asp:Label>
                    <asp:TextBox ID="txtCDC40Dc" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCDC40Dc" ControlToValidate="txtCDC40Dc" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CDC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCDC40Dc" ControlToValidate="txtCDC40Dc" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CDC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>
                </td>
			</tr>
			<tr>
				<td>0.45</td>
				<td>
                    <asp:Label ID="lblCDC45Fc" runat="server">
                    </asp:Label><asp:TextBox ID="txtCDC45Fc" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCDC45Fc" ControlToValidate="txtCDC45Fc" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CDC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCDC45Fc" ControlToValidate="txtCDC45Fc" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CDC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>   
                </td>
                <td>
                    <asp:Label ID="lblCDC45Dc" runat="server">
                    </asp:Label><asp:TextBox ID="txtCDC45Dc" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCDC45Dc" ControlToValidate="txtCDC45Dc" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CDC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCDC45Dc" ControlToValidate="txtCDC45Dc" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CDC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>
                </td>		
			</tr>
			<tr>
				<td>0.50</td>
				<td>
                    <asp:Label ID="lblCDC50Fc" runat="server">
                    </asp:Label><asp:TextBox ID="txtCDC50Fc" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCDC50Fc" ControlToValidate="txtCDC50Fc" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CDC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCDC50Fc" ControlToValidate="txtCDC50Fc" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CDC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>   
                </td>
                <td>
                    <asp:Label ID="lblCDC50Dc" runat="server">
                    </asp:Label><asp:TextBox ID="txtCDC50Dc" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCDC50Dc" ControlToValidate="txtCDC50Dc" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CDC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCDC50Dc" ControlToValidate="txtCDC50Dc" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CDC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>
                </td>
			</tr>
			<tr>
				<td>0.55</td>
				<td>
                    <asp:Label ID="lblCDC55Fc" runat="server">
                    </asp:Label><asp:TextBox ID="txtCDC55Fc" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCDC55Fc" ControlToValidate="txtCDC55Fc" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CDC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCDC55Fc" ControlToValidate="txtCDC55Fc" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CDC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>   
                </td>
                <td>
                    <asp:Label ID="lblCDC55Dc" runat="server">
                    </asp:Label><asp:TextBox ID="txtCDC55Dc" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvCDC55Dc" ControlToValidate="txtCDC55Dc" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="CDC" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regCDC55Dc" ControlToValidate="txtCDC55Dc" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="CDC" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,2}$"></asp:RegularExpressionValidator>
                </td>
			</tr>
		</table>
					
		<div id="divButtons">
			<asp:Button ID="btnCDCEdit" runat="server" Text="Edit" onclick="btnCDCEdit_Click" />
            <asp:Button ID="btnCDCCancel" runat="server" Text="Cancel" Visible="false" onclick="btnCDCCancel_Click" />
		</div>
					
    </ContentTemplate>
</asp:UpdatePanel>

<%--<div id="divSpacer"></div>
<hr />

<asp:UpdatePanel ID="panelEnvFact" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

		<table cellpadding="0" cellspacing="0">
			<tr>
				<th rowspan="2"></th>
				<th colspan="2">n</th>
				<th colspan="2">k<sub>e</sub></th>			
			</tr>
			<tr>
				<th>mean</th>
				<th>SD</th>
				<th>mean</th>
				<th>SD</th>			
			</tr>
			<tr>
				<td>atmospheric</td>
                <td><asp:TextBox ID="txtEnvFact1NMean" class="Textbox Micro" runat="server"></asp:TextBox></td>
                <td><asp:TextBox ID="txtEnvFact1NSD" class="Textbox Micro" runat="server"></asp:TextBox></td>
                <td><asp:TextBox ID="txtEnvFact1KMean" class="Textbox Micro" runat="server"></asp:TextBox></td>
                <td><asp:TextBox ID="txtEnvFact1KSD" class="Textbox Micro" runat="server"></asp:TextBox></td>
			</tr>
			<tr>
				<td>tidal</td>
				<td><asp:TextBox ID="txtEnvFact2NMean" class="Textbox Micro" runat="server"></asp:TextBox></td>
                <td><asp:TextBox ID="txtEnvFact2NSD" class="Textbox Micro" runat="server"></asp:TextBox></td>
                <td><asp:TextBox ID="txtEnvFact2KMean" class="Textbox Micro" runat="server"></asp:TextBox></td>
                <td><asp:TextBox ID="txtEnvFact2KSD" class="Textbox Micro" runat="server"></asp:TextBox></td>
			</tr>
			<tr>
				<td>splash</td>
				<td><asp:TextBox ID="txtEnvFact3NMean" class="Textbox Micro" runat="server"></asp:TextBox></td>
                <td><asp:TextBox ID="txtEnvFact3NSD" class="Textbox Micro" runat="server"></asp:TextBox></td>
                <td><asp:TextBox ID="txtEnvFact3KMean" class="Textbox Micro" runat="server"></asp:TextBox></td>
                <td><asp:TextBox ID="txtEnvFact3KSD" class="Textbox Micro" runat="server"></asp:TextBox></td>
			</tr>
			<tr>
				<td>submerged</td>
				<td><asp:TextBox ID="txtEnvFact4NMean" class="Textbox Micro" runat="server"></asp:TextBox></td>
                <td><asp:TextBox ID="txtEnvFact4NSD" class="Textbox Micro" runat="server"></asp:TextBox></td>
                <td><asp:TextBox ID="txtEnvFact4KMean" class="Textbox Micro" runat="server"></asp:TextBox></td>
                <td><asp:TextBox ID="txtEnvFact4KSD" class="Textbox Micro" runat="server"></asp:TextBox></td>
			</tr>
		</table>
					
		<div id="divButtons">
			<asp:Button ID="btnEnvFactEdit" runat="server" Text="Edit" onclick="btnEnvFactEdit_Click" />
            <asp:Button ID="btnEnvFactCancel" runat="server" Text="Cancel" Visible="false" onclick="btnEnvFactCancel_Click" />
		</div>
					
    </ContentTemplate>
</asp:UpdatePanel>--%>

<asp:UpdatePanel ID="panelLegends" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

		<h3>Legends</h3>
		<hr />
														
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td class="tdLegendItem">R</td>
				<td>0.008314</td>
				<td>kJ/mol K (gas constant)</td>
			</tr>
			<tr>
				<td class="tdLegendItem">C<sub>a</sub>O</td>
				<td>0.65</td>
				<td>(Brown et al 1997)</td>
			</tr>
			<tr>
				<td class="tdLegendItem">M<sub>CaO</sub></td>
				<td>56</td>
				<td>g/mol</td>
			</tr>
			<tr>
				<td class="tdLegendItem">M<sub>CO2</sub></td>
				<td>44</td>
				<td>g/mol</td>
			</tr>
			<tr>
				<td class="tdLegendItem">k<sub>urban</sub></td>
				<td>
                    <asp:Label ID="lblLegKurban" runat="server">
                    </asp:Label><asp:TextBox ID="txtLegKurban" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvLegKurban" ControlToValidate="txtLegKurban" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="LGND" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regLegKurban" ControlToValidate="txtLegKurban" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="LGND" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator>                                
                </td>
				<td>(for arid environment)</td>
			</tr>
			<tr>
				<td class="tdLegendItem">n<sub>m</sub></td>
				<td>
                    <asp:Label ID="lblLegNM" runat="server">
                    </asp:Label><asp:TextBox ID="txtLegNM" class="Textbox Mini" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvLegNM" ControlToValidate="txtLegNM" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="LGND" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="regLegNM" ControlToValidate="txtLegNM" runat="server" ErrorMessage="Invalid value" CssClass="Validation Message NoMargin" 
                        ValidationGroup="LGND" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,4}$"></asp:RegularExpressionValidator>                                
                </td>
				<td>(0 for sheltered outdoor & 0.12 unsheltered outdoor)</td>
			</tr>	
			<tr>
				<td class="tdLegendItem">k<sub>c</sub></td>
				<td>1.0</td>
				<td>(7 days)</td>
			</tr>
			<tr>
				<td class="tdLegendItem">k<sub>t</sub></td>
				<td>1.0</td>
				<td></td>
			</tr>
			<tr>
				<td class="tdLegendItem">t<sub>0</sub></td>
				<td>1</td>
				<td>yr</td>
			</tr>
		</table>

        <div id="divButtons">
			<asp:Button ID="btnConcreteLegendEdit" runat="server" Text="Edit" onclick="btnConcreteLegendEdit_Click" />
            <asp:Button ID="btnConcreteLegendCancel" runat="server" Text="Cancel" Visible="false" onclick="btnConcreteLegendCancel_Click" />
		</div>
					
    </ContentTemplate>
</asp:UpdatePanel>

<div id="divSpacer"></div>
					
<table id="tblUploadControl" cellpadding="0" cellspacing="0">
	<tr>
		<td>
            <asp:Button ID="btnConcreteBack" runat="server" class="Buttons" Text="Back" OnClientClick="return GoBack()" ToolTip="Back to previous page." />
		</td>
		<td align="right">
			<%--<input type="reset" value="Upload Own Properties" class="Buttons AutoSize" onclick="document.getElementById('updProps').click();" title="Continue to next page." />
			<input id="updProps" type="file" class="Upload" style="display:none;" />--%>
			<asp:Button ID="btnConcreteNext" runat="server" Text="Next" onclick="btnNext_Click" class="Buttons" ToolTip="Continue to next page."/>
		</td>
	</tr>
</table>
