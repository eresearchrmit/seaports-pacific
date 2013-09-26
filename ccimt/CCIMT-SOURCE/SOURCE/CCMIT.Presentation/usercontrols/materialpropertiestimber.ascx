<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="materialpropertiestimber.ascx.cs" Inherits="CCIMT.Presentation.usercontrols.materialpropertiestimber" %>
<hr />
<div id="divHeader2Alt">
	Material: Timber
</div>

<%--<asp:UpdatePanel ID="UpdatePanel1" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

        <table cellpadding="0" cellspacing="0">
			<tr>
				<th>Mean summer water temp</th>
				<th>Zone</th>
				<th>k<sub>water</sub></th>
			</tr>
			<tr>
				<td>(°C)</td><td></td><td></td>
			</tr>
			<tr>
				<td>15</td>
				<td>A</td>
                <td><asp:TextBox ID="txt" class="Textbox Mini" runat="server"></asp:TextBox></td>
				<td><input name="Text1" type="text" value="0.7" class="Textbox Mini" /></td>
			</tr>
			<tr>
				<td>17</td>
				<td>B</td>
				<td><input name="Text1" type="text" value="0.9" class="Textbox Mini" /></td>
			</tr>
			<tr>
				<td>19</td>
				<td>C</td>
				<td><input name="Text1" type="text" value="1.2" class="Textbox Mini" /></td>
			</tr>
			<tr>
				<td>21</td>
				<td>D</td>
				<td><input name="Text1" type="text" value="1.6" class="Textbox Mini" /></td>
			</tr>
			<tr>
				<td>23</td>
				<td>E</td>
				<td><input name="Text1" type="text" value="2.0" class="Textbox Mini" /></td>
			</tr>
			<tr>
				<td>28</td>
				<td>F</td>
				<td><input name="Text1" type="text" value="3.0" class="Textbox Mini" /></td>
			</tr>
			<tr>
				<td>28</td>
				<td>G</td>
				<td><input name="Text1" type="text" value="3.8" class="Textbox Mini" /></td>
			</tr>
		</table>

		<div id="div3">
			<input name="et1" id="et1" type="button" value="Edit" onclick="javascript:Edit_Click('et1', 'ct1');" />
			<input name="ct1" id="ct1" type="button" value="Cancel" onclick="javascript:Cancel_Click('et1', 'ct1');" style="display:none;" />
		</div>
				        
    </ContentTemplate>
</asp:UpdatePanel>--%>
				
<asp:UpdatePanel ID="panelTimberTable1" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

        <h3>Material Parameters For Timber Types</h3>
		<hr />

		<table cellpadding="0" cellspacing="0">
			<tr>
			<th colspan="3" rowspan="2">Material</th>
			<th colspan="2">k<sub>timber</sub></th>
		</tr>
		<tr>
			<th>Zones A-C</th>
			<th>Zones D-G</th>
		</tr>
		<tr>
			<td rowspan="4">Heartwood</td>
			<td>Class 1</td>
			<td>HW1</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC1" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC1" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC1" ControlToValidate="txtTimberTable1ZAC1" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC1" ControlToValidate="txtTimberTable1ZAC1" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG1" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG1" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG1" ControlToValidate="txtTimberTable1ZDG1" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG1" ControlToValidate="txtTimberTable1ZDG1" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>				                
		</tr>
		<tr>
			<td>Class 2</td>
			<td>HW2</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC2" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC2" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC2" ControlToValidate="txtTimberTable1ZAC2" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC2" ControlToValidate="txtTimberTable1ZAC2" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG2" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG2" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG2" ControlToValidate="txtTimberTable1ZDG2" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG2" ControlToValidate="txtTimberTable1ZDG2" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>				                 
		</tr>
		<tr>
			<td>Class 3</td>
			<td>HW3</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC3" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC3" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC3" ControlToValidate="txtTimberTable1ZAC3" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC3" ControlToValidate="txtTimberTable1ZAC3" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG3" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG3" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG3" ControlToValidate="txtTimberTable1ZDG3" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG3" ControlToValidate="txtTimberTable1ZDG3" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>				                
		</tr>
		<tr>
			<td>Class 4</td>
			<td>HW4</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC4" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC4" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC4" ControlToValidate="txtTimberTable1ZAC4" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC4" ControlToValidate="txtTimberTable1ZAC4" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG4" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG4" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG4" ControlToValidate="txtTimberTable1ZDG4" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG4" ControlToValidate="txtTimberTable1ZDG4" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>				                				                
		</tr>
		<tr>
			<td rowspan="8" align="center">Sapwood <br />of <br /> softwood</td>
			<td>Untreated</td>
			<td>SPSU</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC5" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC5" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC5" ControlToValidate="txtTimberTable1ZAC5" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC5" ControlToValidate="txtTimberTable1ZAC5" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG5" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG5" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG5" ControlToValidate="txtTimberTable1ZDG5" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG5" ControlToValidate="txtTimberTable1ZDG5" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>				                
		</tr>
		<tr>
			<td>Creosote-treated 24%kg/kg</td>
			<td>SPSCR1</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC6" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC6" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC6" ControlToValidate="txtTimberTable1ZAC6" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC6" ControlToValidate="txtTimberTable1ZAC6" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG6" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG6" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG6" ControlToValidate="txtTimberTable1ZDG6" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG6" ControlToValidate="txtTimberTable1ZDG6" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>				                
		</tr>
		<tr>
        	<td>Creosote-treated 40%kg/kg</td>
		    <td>SPSCR2</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC7" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC7" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC7" ControlToValidate="txtTimberTable1ZAC7" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC7" ControlToValidate="txtTimberTable1ZAC7" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG7" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG7" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG7" ControlToValidate="txtTimberTable1ZDG7" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG7" ControlToValidate="txtTimberTable1ZDG7" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>				                
		</tr>
		<tr>
        	<td>CCA-treated 0.6%kg/kg</td>
	        <td>SPSCC1</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC8" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC8" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC8" ControlToValidate="txtTimberTable1ZAC8" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC8" ControlToValidate="txtTimberTable1ZAC8" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG8" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG8" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG8" ControlToValidate="txtTimberTable1ZDG8" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG8" ControlToValidate="txtTimberTable1ZDG8" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>				                
		</tr>
		<tr>
    		<td>CCA-treated 1%kg/kg</td>
	    	<td>SPSCC2</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC9" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC9" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC9" ControlToValidate="txtTimberTable1ZAC9" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC9" ControlToValidate="txtTimberTable1ZAC9" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG9" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG9" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG9" ControlToValidate="txtTimberTable1ZDG9" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG9" ControlToValidate="txtTimberTable1ZDG9" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>				                
		</tr>
		<tr>
    		<td>CCA-treated 2%kg/kg</td>
	    	<td>SPSCC3</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC10" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC10" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC10" ControlToValidate="txtTimberTable1ZAC10" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC10" ControlToValidate="txtTimberTable1ZAC10" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG10" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG10" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG10" ControlToValidate="txtTimberTable1ZDG10" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG10" ControlToValidate="txtTimberTable1ZDG10" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>				                
		</tr>				
		<tr>
			<td>CCA-treated 5%kg/kg</td>
			<td>SPSCC4</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC11" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC11" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC11" ControlToValidate="txtTimberTable1ZAC11" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC11" ControlToValidate="txtTimberTable1ZAC11" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG11" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG11" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG11" ControlToValidate="txtTimberTable1ZDG11" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG11" ControlToValidate="txtTimberTable1ZDG11" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>				                
		</tr>
		<tr>
			<td>Double-treated CC2+CR2</td>
			<td>SPSDBT</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC12" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC12" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC12" ControlToValidate="txtTimberTable1ZAC12" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC12" ControlToValidate="txtTimberTable1ZAC12" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG12" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG12" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG12" ControlToValidate="txtTimberTable1ZDG12" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG12" ControlToValidate="txtTimberTable1ZDG12" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>				                
		</tr>
		<tr>
			<td rowspan="7" align="center">Sapwood <br />of <br />hardwood</td>
			<td>Untreated</td>
			<td>SPHU</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC13" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC13" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC13" ControlToValidate="txtTimberTable1ZAC13" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC13" ControlToValidate="txtTimberTable1ZAC13" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG13" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG13" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG13" ControlToValidate="txtTimberTable1ZDG13" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG13" ControlToValidate="txtTimberTable1ZDG13" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>				                
		</tr>
		<tr>
			<td>Creosote-treated 13%kg/kg</td>
			<td>SPHCR1</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC14" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC14" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC14" ControlToValidate="txtTimberTable1ZAC14" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC14" ControlToValidate="txtTimberTable1ZAC14" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG14" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG14" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG14" ControlToValidate="txtTimberTable1ZDG14" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG14" ControlToValidate="txtTimberTable1ZDG14" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>				                
		</tr>
		<tr>
			<td>Creosote-treated 22%kg/kg</td>
			<td>SPHCR2</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC15" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC15" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC15" ControlToValidate="txtTimberTable1ZAC15" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC15" ControlToValidate="txtTimberTable1ZAC15" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG15" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG15" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG15" ControlToValidate="txtTimberTable1ZDG15" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG15" ControlToValidate="txtTimberTable1ZDG15" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
		</tr>
		<tr>
			<td>CCA-treated 0.7%kg/kg</td>
			<td>SPHCC1</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC16" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC16" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC16" ControlToValidate="txtTimberTable1ZAC16" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC16" ControlToValidate="txtTimberTable1ZAC16" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG16" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG16" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG16" ControlToValidate="txtTimberTable1ZDG16" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG16" ControlToValidate="txtTimberTable1ZDG16" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
		</tr>
		<tr>
			<td>CCA-treated 1.2%kg/kg</td>
			<td>SPHCC2</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC17" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC17" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC17" ControlToValidate="txtTimberTable1ZAC17" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC17" ControlToValidate="txtTimberTable1ZAC17" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG17" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG17" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG17" ControlToValidate="txtTimberTable1ZDG17" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG17" ControlToValidate="txtTimberTable1ZDG17" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
		</tr>
		<tr>
			<td>CCA-treated 2.4%kg/kg</td>
			<td>SPHCC3</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC18" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC18" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC18" ControlToValidate="txtTimberTable1ZAC18" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC18" ControlToValidate="txtTimberTable1ZAC18" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG18" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG18" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG18" ControlToValidate="txtTimberTable1ZDG18" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG18" ControlToValidate="txtTimberTable1ZDG18" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
		</tr>				
		<tr>
			<td>Double-treated CC2+CR2</td>
			<td>SPHDBT</td>
            <td>
                <asp:Label ID="lblTimberTable1ZAC19" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZAC19" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZAC19" ControlToValidate="txtTimberTable1ZAC19" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZAC19" ControlToValidate="txtTimberTable1ZAC19" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
            <td>
                <asp:Label ID="lblTimberTable1ZDG19" runat="server"></asp:Label>
                <asp:TextBox ID="txtTimberTable1ZDG19" class="Textbox Micro" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="rfvTimberTable1ZDG19" ControlToValidate="txtTimberTable1ZDG19" runat="server" ErrorMessage="*" Display="Dynamic" 
                    CssClass="Validation Message NoMargin" ValidationGroup="Table1" SetFocusOnError="True"></asp:RequiredFieldValidator><br />
                <asp:RegularExpressionValidator ID="regTimberTable1ZDG19" ControlToValidate="txtTimberTable1ZDG19" runat="server" ErrorMessage="Invalid Input." 
                    CssClass="Message NoMargin" ValidationGroup="Table1" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                </asp:RegularExpressionValidator>
            </td>
		</tr>
		</table>
		*&nbsp;Refer to AS5604-2005
		<br/>
				
		<div id="divButtons">
            <asp:Button ID="btnTimberTable1Edit" runat="server" Text="Edit" onclick="btnTimberTable1Edit_Click" />
            <asp:Button ID="btnTimberTable1Cancel" runat="server" Text="Cancel" Visible="false" onclick="btnTimberTable1Cancel_Click" />
		</div>
				     
    </ContentTemplate>
</asp:UpdatePanel>

<div id="divSpacer"></div>
    		 
<asp:UpdatePanel ID="panelTimberTable2" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

        <h3>Salinity Concentration Factors</h3>
		<hr />

		<table cellpadding="0" cellspacing="0">
			<tr>
				<th rowspan="2">Class</th>
				<th rowspan="2">Salinity<br/>(ppt)</th>
				<th colspan="2">k<sub>sal</sub></th>
			</tr>
			<tr>
				<th>Zones A-D</th>
				<th>Zones E-G</th>
			</tr>
			<tr>
				<td>Class 1</td>
				<td>1-10</td>                                    	
                <td>
                    <asp:Label ID="lblTimberTable2KZAD1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable2KZAD1" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable2KZAD1" ControlToValidate="txtTimberTable2KZAD1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table2" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable2KZAD1" ControlToValidate="txtTimberTable2KZAD1" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table2" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
                <td>
                    <asp:Label ID="lblTimberTable2KZEG1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable2KZEG1" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable2KZEG1" ControlToValidate="txtTimberTable2KZEG1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table2" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable2KZEG1" ControlToValidate="txtTimberTable2KZEG1" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table2" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>	                    
			</tr>
			<tr>
        		<td>Class 2</td>
				<td>11-25</td>
				<td>    
                    <asp:Label ID="lblTimberTable2KZAD2" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable2KZAD2" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable2KZAD2" ControlToValidate="txtTimberTable2KZAD2" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table2" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable2KZAD2" ControlToValidate="txtTimberTable2KZAD2" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table2" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
                <td>
                    <asp:Label ID="lblTimberTable2KZEG2" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable2KZEG2" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable2KZEG2" ControlToValidate="txtTimberTable2KZEG2" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table2" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable2KZEG2" ControlToValidate="txtTimberTable2KZEG2" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table2" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
			</tr>
			<tr>
				<td>Class 3</td>
				<td>&gt;26</td>
				<td>
                    <asp:Label ID="lblTimberTable2KZAD3" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable2KZAD3" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable2KZAD3" ControlToValidate="txtTimberTable2KZAD3" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table2" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable2KZAD3" ControlToValidate="txtTimberTable2KZAD3" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table2" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
                <td>
                    <asp:Label ID="lblTimberTable2KZEG3" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable2KZEG3" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable2KZEG3" ControlToValidate="txtTimberTable2KZEG3" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table2" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable2KZEG3" ControlToValidate="txtTimberTable2KZEG3" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table2" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
			</tr>      
		</table>

		<div id="divButtons">
            <asp:Button ID="btnTimberTable2Edit" runat="server" Text="Edit" onclick="btnTimberTable2Edit_Click" />
            <asp:Button ID="btnTimberTable2Cancel" runat="server" Text="Cancel" Visible="false" onclick="btnTimberTable2Cancel_Click" />
		</div>
				     
    </ContentTemplate>
</asp:UpdatePanel>

<div id="divSpacer"></div>
				     
<asp:UpdatePanel ID="panelTimberTable3" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

        <h3>Shelter Parameter</h3>
		<hr />

		<table cellpadding="0" cellspacing="0">
			<tr>
				<th colspan="2">Water zone</th>
				<th>k<sub>shelter</sub></th>
			</tr>
			<tr>
				<td>Calm or sheltered from wave</td>
				<td>EXP1</td>
				<td>
                    <asp:Label ID="lblTimberTable3KS1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable3KS1" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable3KS1" ControlToValidate="txtTimberTable3KS1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table3" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable3KS1" ControlToValidate="txtTimberTable3KS1" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table3" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>                                    
			</tr>
			<tr>
				<td>Exposed to strong wave or surf</td>
				<td>EXP2</td>
				<td>
                    <asp:Label ID="lblTimberTable3KS2" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable3KS2" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable3KS2" ControlToValidate="txtTimberTable3KS2" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table3" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable3KS2" ControlToValidate="txtTimberTable3KS2" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table3" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>                                    
			</tr>      
		</table>

		<div id="divButtons">
			<asp:Button ID="btnTimberTable3Edit" runat="server" Text="Edit" onclick="btnTimberTable3Edit_Click" />
            <asp:Button ID="btnTimberTable3Cancel" runat="server" Text="Cancel" Visible="false" onclick="btnTimberTable3Cancel_Click" />
		</div>
				     
    </ContentTemplate>
</asp:UpdatePanel>

<div id="divSpacer"></div>
				
<asp:UpdatePanel ID="panelTimberTable4" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

        <h3>Protection Parameter</h3>
		<hr />

		<table cellpadding="0" cellspacing="0">
			<tr>
				<th colspan="2">Maintenance measure</th>
				<th>k<sub>protect</sub></th>
			</tr>
			<tr>
				<td>Floating collar</td>
				<td>MTN1</td>
				<td>
                    <asp:Label ID="lblTimberTable4KP1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable4KP1" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable4KP1" ControlToValidate="txtTimberTable4KP1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table4" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable4KP1" ControlToValidate="txtTimberTable4KP1" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table4" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
			</tr>
			<tr>
				<td>None</td>
				<td>MTN2</td>
				<td>
                    <asp:Label ID="lblTimberTable4KP2" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable4KP2" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable4KP2" ControlToValidate="txtTimberTable4KP2" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table4" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable4KP2" ControlToValidate="txtTimberTable4KP2" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table4" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
			</tr>      
		</table>

		<div id="divButtons">
			<asp:Button ID="btnTimberTable4Edit" runat="server" Text="Edit" onclick="btnTimberTable4Edit_Click" />
            <asp:Button ID="btnTimberTable4Cancel" runat="server" Text="Cancel" Visible="false" onclick="btnTimberTable4Cancel_Click" />
		</div>
				     
    </ContentTemplate>
</asp:UpdatePanel>

<div id="divSpacer"></div>
				     
<asp:UpdatePanel ID="panelTimberTable5" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

        <h3>Connection Parameter</h3>
		<hr />

		<table cellpadding="0" cellspacing="0">
			<tr>
				<th colspan="2">Contact</th>
				<th>k<sub>contact</sub></th>
			</tr>
			<tr>
				<td>Contact with other timber member</td>
				<td>CNT1</td>
				<td>
                    <asp:Label ID="lblTimberTable5KC1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable5KC1" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable5KC1" ControlToValidate="txtTimberTable5KC1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table5" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable5KC1" ControlToValidate="txtTimberTable5KC1" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table5" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
			</tr>
			<tr>
				<td>None</td>
				<td>CNT2</td>
				<td>
                    <asp:Label ID="lblTimberTable5KC2" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable5KC2" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable5KC2" ControlToValidate="txtTimberTable5KC2" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table5" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable5KC2" ControlToValidate="txtTimberTable5KC2" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table5" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
			</tr>      
		</table>

		<div id="divButtons">
			<asp:Button ID="btnTimberTable5Edit" runat="server" Text="Edit" onclick="btnTimberTable5Edit_Click" />
            <asp:Button ID="btnTimberTable5Cancel" runat="server" Text="Cancel" Visible="false" onclick="btnTimberTable5Cancel_Click" />
		</div>
				     
    </ContentTemplate>
</asp:UpdatePanel>

<div id="divSpacer"></div>
				     
<asp:UpdatePanel ID="panelTimberTable6" runat="server" UpdateMode="Conditional">
    <ContentTemplate>

        <h3>Knot Parameter</h3>
		<hr />

		<table cellpadding="0" cellspacing="0">
			<tr>
				<th colspan="2">Knot presence</th>
				<th>k<sub>knot</sub></th>
			</tr>
			<tr>
				<td>Having knots without protective plate</td>
				<td>KNT1</td>
				<td>
                    <asp:Label ID="lblTimberTable6KK1" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable6KK1" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable6KK1" ControlToValidate="txtTimberTable6KK1" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table6" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable6KK1" ControlToValidate="txtTimberTable6KK1" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table6" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
			</tr>
			<tr>
				<td>Having knots with protective plate</td>
				<td>KNT2</td>
				<td>
                    <asp:Label ID="lblTimberTable6KK2" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable6KK2" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable6KK2" ControlToValidate="txtTimberTable6KK2" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table6" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable6KK2" ControlToValidate="txtTimberTable6KK2" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table6" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
			</tr>
			<tr>
				<td>None</td>
				<td>KNT3</td>
				<td>
                    <asp:Label ID="lblTimberTable6KK3" runat="server"></asp:Label>
                    <asp:TextBox ID="txtTimberTable6KK3" class="Textbox Mini2" runat="server"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="rfvTimberTable6KK3" ControlToValidate="txtTimberTable6KK3" runat="server" ErrorMessage="*" Display="Dynamic" 
                        CssClass="Validation Message NoMargin" ValidationGroup="Table6" SetFocusOnError="True"></asp:RequiredFieldValidator>
                    <asp:RegularExpressionValidator ID="revTimberTable6KK3" ControlToValidate="txtTimberTable6KK3" runat="server" ErrorMessage="Invalid Input." 
                        CssClass="Message NoMargin" ValidationGroup="Table6" Display="Dynamic" SetFocusOnError="True" ValidationExpression="^\d+\.?\d{0,3}$" >
                    </asp:RegularExpressionValidator>
                </td>
			</tr>      
		</table>
                     
		<div id="divButtons">
			<asp:Button ID="btnTimberTable6Edit" runat="server" Text="Edit" onclick="btnTimberTable6Edit_Click" />
            <asp:Button ID="btnTimberTable6Cancel" runat="server" Text="Cancel" Visible="false" onclick="btnTimberTable6Cancel_Click" />
		</div>
				     
    </ContentTemplate>
</asp:UpdatePanel>

<div id="divSpacer" align="left"></div>

	<div align="left">
		<h3>Legend</h3>
		<hr />
				              
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td class="tdLegendItem">r</td>
				<td>Attack rate of marine borer</td>
			</tr>
			<tr>
				<td class="tdLegendItem">V<sub>d</sub></td>
				<td>coefficient of variation</td>
			</tr>
			<tr>
				<td class="tdLegendItem">α</td>
				<td>reliability factor</td>
			</tr>
			<tr>
				<td class="tdLegendItem">d<sub>sap</sub></td>
				<td>layer of sapwood</td>
			</tr>
			<tr>
				<td class="tdLegendItem">D<sub>design</sub></td>
				<td>design diameter</td>
			</tr>
			<tr>
				<td class="tdLegendItem">D<sub>o</sub></td>
				<td>initial diameter</td>
			</tr>
		</table>
	</div>
				     
<div id="divSpacer"></div>
				     
<table id="tblUploadControl" cellpadding="0" cellspacing="0">
	<tr>
		<td>
            <asp:Button ID="btnTimberBack" runat="server" class="Buttons" Text="Back" OnClientClick="return GoBack()" ToolTip="Back to previous page." />
		</td>
		<td align="right">
			<%--<input type="reset" value="Upload Own Properties" class="Buttons AutoSize" onclick="document.getElementById('updProps').click();" title="Continue to next page." />--%>
			<%--<input id="File1" type="file" class="Upload" style="display:none;" />--%>
            <asp:Button ID="btnTimberNext" runat="server" Text="Next" onclick="btnNext_Click" class="Buttons" ToolTip="Continue to next page."/>
		</td>
	</tr>
</table>