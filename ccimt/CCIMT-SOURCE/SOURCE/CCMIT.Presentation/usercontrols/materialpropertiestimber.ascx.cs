using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.IO;

namespace CCIMT.Presentation.usercontrols
{
    public partial class materialpropertiestimber : BaseControl
    {

        #region Page Events

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                EnableTimberEditMode(false);
            }
            else
            {
            }
        }

        protected void btnNext_Click(object sender, EventArgs e)
        {
            Response.Redirect("structuredefinitions.aspx");
        }

        #endregion

        #region Button Click Events

        protected void btnTimberTable1Edit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnTimberTable1Edit.Text, Common.EVENT_NAME_EDIT))
            {
                SetTimberEditMode(true, TableTypeTimber.Table1MaterialKwood);
                LoadDefaultTimberTextValues(TableTypeTimber.Table1MaterialKwood, false);
                btnTimberTable1Edit.Text = Common.EVENT_NAME_SAVE;
                btnTimberTable1Edit.ValidationGroup = "Table1";
                btnTimberTable1Cancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                    UpdateTable1();

                SetTimberEditMode(false, TableTypeTimber.Table1MaterialKwood);
                TimberTemplate currentTemplate = CurrentTimberTemplate;
                LoadDefaultTimberLabelValues(currentTemplate, TableTypeTimber.Table1MaterialKwood, false);
                btnTimberTable1Edit.Text = Common.EVENT_NAME_EDIT;
                btnTimberTable1Edit.ValidationGroup = String.Empty;
                btnTimberTable1Cancel.Visible = false;  
            }
        }

        protected void btnTimberTable1Cancel_Click(object sender, EventArgs e)
        {
            SetTimberEditMode(false, TableTypeTimber.Table1MaterialKwood);
            TimberTemplate currentTemplate = CurrentTimberTemplate;
            LoadDefaultTimberLabelValues(currentTemplate, TableTypeTimber.Table1MaterialKwood, false);
            btnTimberTable1Edit.Text = Common.EVENT_NAME_EDIT;
            btnTimberTable1Edit.ValidationGroup = String.Empty;
            btnTimberTable1Cancel.Visible = false;
        }

        protected void btnTimberTable2Edit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnTimberTable2Edit.Text, Common.EVENT_NAME_EDIT))
            {
                SetTimberEditMode(true, TableTypeTimber.Table2ClassSalinityKsal);
                LoadDefaultTimberTextValues(TableTypeTimber.Table2ClassSalinityKsal, false);
                btnTimberTable2Edit.Text = Common.EVENT_NAME_SAVE;
                btnTimberTable2Edit.ValidationGroup = "Table2";
                btnTimberTable2Cancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                    UpdateTable2();

                SetTimberEditMode(false, TableTypeTimber.Table2ClassSalinityKsal);
                TimberTemplate currentTemplate = CurrentTimberTemplate;
                LoadDefaultTimberLabelValues(currentTemplate, TableTypeTimber.Table2ClassSalinityKsal, false);
                btnTimberTable2Edit.Text = Common.EVENT_NAME_EDIT;
                btnTimberTable2Edit.ValidationGroup = String.Empty;
                btnTimberTable2Cancel.Visible = false;  
            }
        }

        protected void btnTimberTable2Cancel_Click(object sender, EventArgs e)
        {
            SetTimberEditMode(false, TableTypeTimber.Table2ClassSalinityKsal);
            TimberTemplate currentTemplate = CurrentTimberTemplate;
            LoadDefaultTimberLabelValues(currentTemplate, TableTypeTimber.Table2ClassSalinityKsal, false);
            btnTimberTable2Edit.Text = Common.EVENT_NAME_EDIT;
            btnTimberTable2Edit.ValidationGroup = String.Empty;
            btnTimberTable2Cancel.Visible = false;
        }

        protected void btnTimberTable3Edit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnTimberTable3Edit.Text, Common.EVENT_NAME_EDIT))
            {
                SetTimberEditMode(true, TableTypeTimber.Table3WaterZoneKshelter);
                LoadDefaultTimberTextValues(TableTypeTimber.Table3WaterZoneKshelter, false);
                btnTimberTable3Edit.Text = Common.EVENT_NAME_SAVE;
                btnTimberTable3Edit.ValidationGroup = "Table3";
                btnTimberTable3Cancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                    UpdateTable3();

                SetTimberEditMode(false, TableTypeTimber.Table3WaterZoneKshelter);
                TimberTemplate currentTemplate = CurrentTimberTemplate;
                LoadDefaultTimberLabelValues(currentTemplate, TableTypeTimber.Table3WaterZoneKshelter, false);
                btnTimberTable3Edit.Text = Common.EVENT_NAME_EDIT;
                btnTimberTable3Edit.ValidationGroup = String.Empty;
                btnTimberTable3Cancel.Visible = false; 
            }
        }

        protected void btnTimberTable3Cancel_Click(object sender, EventArgs e)
        {
            SetTimberEditMode(false, TableTypeTimber.Table3WaterZoneKshelter);
            TimberTemplate currentTemplate = CurrentTimberTemplate;
            LoadDefaultTimberLabelValues(currentTemplate, TableTypeTimber.Table3WaterZoneKshelter, false);
            btnTimberTable3Edit.Text = Common.EVENT_NAME_EDIT;
            btnTimberTable3Edit.ValidationGroup = String.Empty;
            btnTimberTable3Cancel.Visible = false;
        }

        protected void btnTimberTable4Edit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnTimberTable4Edit.Text, Common.EVENT_NAME_EDIT))
            {
                SetTimberEditMode(true, TableTypeTimber.Table4MaintenanceMeasureKprotext);
                LoadDefaultTimberTextValues(TableTypeTimber.Table4MaintenanceMeasureKprotext, false);
                btnTimberTable4Edit.Text = Common.EVENT_NAME_SAVE;
                btnTimberTable4Edit.ValidationGroup = "Table4";
                btnTimberTable4Cancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                    UpdateTable4();

                SetTimberEditMode(false, TableTypeTimber.Table4MaintenanceMeasureKprotext);
                TimberTemplate currentTemplate = CurrentTimberTemplate;
                LoadDefaultTimberLabelValues(currentTemplate, TableTypeTimber.Table4MaintenanceMeasureKprotext, false);
                btnTimberTable4Edit.Text = Common.EVENT_NAME_EDIT;
                btnTimberTable4Edit.ValidationGroup = String.Empty;
                btnTimberTable4Cancel.Visible = false;
            }
        }

        protected void btnTimberTable4Cancel_Click(object sender, EventArgs e)
        {
            SetTimberEditMode(false, TableTypeTimber.Table4MaintenanceMeasureKprotext);
            TimberTemplate currentTemplate = CurrentTimberTemplate;
            LoadDefaultTimberLabelValues(currentTemplate, TableTypeTimber.Table4MaintenanceMeasureKprotext, false);
            btnTimberTable4Edit.Text = Common.EVENT_NAME_EDIT;
            btnTimberTable4Edit.ValidationGroup = String.Empty;
            btnTimberTable4Cancel.Visible = false;
        }

        protected void btnTimberTable5Edit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnTimberTable5Edit.Text, Common.EVENT_NAME_EDIT))
            {
                SetTimberEditMode(true, TableTypeTimber.Table5ContractKcontract);
                LoadDefaultTimberTextValues(TableTypeTimber.Table5ContractKcontract, false);
                btnTimberTable5Edit.Text = Common.EVENT_NAME_SAVE;
                btnTimberTable5Edit.ValidationGroup = "Table5";
                btnTimberTable5Cancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                    UpdateTable5();

                SetTimberEditMode(false, TableTypeTimber.Table5ContractKcontract);
                TimberTemplate currentTemplate = CurrentTimberTemplate;
                LoadDefaultTimberLabelValues(currentTemplate, TableTypeTimber.Table5ContractKcontract, false);
                btnTimberTable5Edit.Text = Common.EVENT_NAME_EDIT;
                btnTimberTable5Edit.ValidationGroup = String.Empty;
                btnTimberTable5Cancel.Visible = false;
            }
        }

        protected void btnTimberTable5Cancel_Click(object sender, EventArgs e)
        {
            SetTimberEditMode(false, TableTypeTimber.Table5ContractKcontract);
            TimberTemplate currentTemplate = CurrentTimberTemplate;
            LoadDefaultTimberLabelValues(currentTemplate, TableTypeTimber.Table5ContractKcontract, false);
            btnTimberTable5Edit.Text = Common.EVENT_NAME_EDIT;
            btnTimberTable5Edit.ValidationGroup = String.Empty;
            btnTimberTable5Cancel.Visible = false;
        }

        protected void btnTimberTable6Edit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnTimberTable6Edit.Text, Common.EVENT_NAME_EDIT))
            {
                SetTimberEditMode(true, TableTypeTimber.Table6KnotPresenceKnot);
                LoadDefaultTimberTextValues(TableTypeTimber.Table6KnotPresenceKnot, false);
                btnTimberTable6Edit.Text = Common.EVENT_NAME_SAVE;
                btnTimberTable6Edit.ValidationGroup = "Table6";
                btnTimberTable6Cancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                    UpdateTable6();

                SetTimberEditMode(false, TableTypeTimber.Table6KnotPresenceKnot);
                TimberTemplate currentTemplate = CurrentTimberTemplate;
                LoadDefaultTimberLabelValues(currentTemplate, TableTypeTimber.Table6KnotPresenceKnot, false);
                btnTimberTable6Edit.Text = Common.EVENT_NAME_EDIT;
                btnTimberTable6Edit.ValidationGroup = String.Empty;
                btnTimberTable6Cancel.Visible = false;
            }
        }

        protected void btnTimberTable6Cancel_Click(object sender, EventArgs e)
        {
            SetTimberEditMode(false, TableTypeTimber.Table6KnotPresenceKnot);
            TimberTemplate currentTemplate = CurrentTimberTemplate;
            LoadDefaultTimberLabelValues(currentTemplate, TableTypeTimber.Table6KnotPresenceKnot, false);
            btnTimberTable6Edit.Text = Common.EVENT_NAME_EDIT;
            btnTimberTable6Edit.ValidationGroup = String.Empty;
            btnTimberTable6Cancel.Visible = false;
        }

        #endregion

        #region Update Methods

        private void UpdateTimberTable1()
        {
            decimal timberTbl1ZAC1 = Convert.ToDecimal(txtTimberTable1ZAC1.Text);
            decimal timberTbl1ZDG1 = Convert.ToDecimal(txtTimberTable1ZDG1.Text);
            decimal timberTbl1ZAC2 = Convert.ToDecimal(txtTimberTable1ZAC2.Text);
            decimal timberTbl1ZDG2 = Convert.ToDecimal(txtTimberTable1ZDG2.Text);
            decimal timberTbl1ZAC3 = Convert.ToDecimal(txtTimberTable1ZAC3.Text);
            decimal timberTbl1ZDG3 = Convert.ToDecimal(txtTimberTable1ZDG3.Text);
            decimal timberTbl1ZAC4 = Convert.ToDecimal(txtTimberTable1ZAC4.Text);
            decimal timberTbl1ZDG4 = Convert.ToDecimal(txtTimberTable1ZDG4.Text);
            decimal timberTbl1ZAC5 = Convert.ToDecimal(txtTimberTable1ZAC5.Text);
            decimal timberTbl1ZDG5 = Convert.ToDecimal(txtTimberTable1ZDG5.Text);
            decimal timberTbl1ZAC6 = Convert.ToDecimal(txtTimberTable1ZAC6.Text);
            decimal timberTbl1ZDG6 = Convert.ToDecimal(txtTimberTable1ZDG6.Text);
            decimal timberTbl1ZAC7 = Convert.ToDecimal(txtTimberTable1ZAC7.Text);
            decimal timberTbl1ZDG7 = Convert.ToDecimal(txtTimberTable1ZDG7.Text);
            decimal timberTbl1ZAC8 = Convert.ToDecimal(txtTimberTable1ZAC8.Text);
            decimal timberTbl1ZDG8 = Convert.ToDecimal(txtTimberTable1ZDG8.Text);
            decimal timberTbl1ZAC9 = Convert.ToDecimal(txtTimberTable1ZAC9.Text);
            decimal timberTbl1ZDG9 = Convert.ToDecimal(txtTimberTable1ZDG9.Text);
            decimal timberTbl1ZAC10 = Convert.ToDecimal(txtTimberTable1ZAC10.Text);
            decimal timberTbl1ZDG10 = Convert.ToDecimal(txtTimberTable1ZDG10.Text);
            decimal timberTbl1ZAC11 = Convert.ToDecimal(txtTimberTable1ZAC11.Text);
            decimal timberTbl1ZDG11 = Convert.ToDecimal(txtTimberTable1ZDG11.Text);
            decimal timberTbl1ZAC12 = Convert.ToDecimal(txtTimberTable1ZAC12.Text);
            decimal timberTbl1ZDG12 = Convert.ToDecimal(txtTimberTable1ZDG12.Text);
            decimal timberTbl1ZAC13 = Convert.ToDecimal(txtTimberTable1ZAC13.Text);
            decimal timberTbl1ZDG13 = Convert.ToDecimal(txtTimberTable1ZDG13.Text);
            decimal timberTbl1ZAC14 = Convert.ToDecimal(txtTimberTable1ZAC14.Text);
            decimal timberTbl1ZDG14 = Convert.ToDecimal(txtTimberTable1ZDG14.Text);
            decimal timberTbl1ZAC15 = Convert.ToDecimal(txtTimberTable1ZAC15.Text);
            decimal timberTbl1ZDG15 = Convert.ToDecimal(txtTimberTable1ZDG15.Text);
            decimal timberTbl1ZAC16 = Convert.ToDecimal(txtTimberTable1ZAC16.Text);
            decimal timberTbl1ZDG16 = Convert.ToDecimal(txtTimberTable1ZDG16.Text);
            decimal timberTbl1ZAC17 = Convert.ToDecimal(txtTimberTable1ZAC17.Text);
            decimal timberTbl1ZDG17 = Convert.ToDecimal(txtTimberTable1ZDG17.Text);
            decimal timberTbl1ZAC18 = Convert.ToDecimal(txtTimberTable1ZAC18.Text);
            decimal timberTbl1ZDG18 = Convert.ToDecimal(txtTimberTable1ZDG18.Text);
            decimal timberTbl1ZAC19 = Convert.ToDecimal(txtTimberTable1ZAC19.Text);
            decimal timberTbl1ZDG19 = Convert.ToDecimal(txtTimberTable1ZDG19.Text);
        }

        private void UpdateTimberTable2()
        {
            decimal timberTbl2KZAD1 = Convert.ToDecimal(txtTimberTable2KZAD1.Text);
            decimal timberTbl2KZEG1 = Convert.ToDecimal(txtTimberTable2KZEG1.Text);
            decimal timberTbl2KZAD2 = Convert.ToDecimal(txtTimberTable2KZAD2.Text);
            decimal timberTbl2KZEG2 = Convert.ToDecimal(txtTimberTable2KZEG2.Text);
            decimal timberTbl2KZAD3 = Convert.ToDecimal(txtTimberTable2KZAD3.Text);
            decimal timberTbl2KZEG3 = Convert.ToDecimal(txtTimberTable2KZEG3.Text);
        }

        private void UpdateTimberTable3()
        {
            decimal timberTbl3KS1 = Convert.ToDecimal(txtTimberTable3KS1.Text);
            decimal timberTbl3KS2 = Convert.ToDecimal(txtTimberTable3KS2.Text);
        }

        private void UpdateTimberTable4()
        {
            decimal timberTbl4KP1 = Convert.ToDecimal(txtTimberTable4KP1.Text);
            decimal timberTbl4KP2 = Convert.ToDecimal(txtTimberTable4KP2.Text);
        }

        private void UpdateTimberTable5()
        {
            decimal timberTbl5KC1 = Convert.ToDecimal(txtTimberTable5KC1.Text);
            decimal timberTbl5KC2 = Convert.ToDecimal(txtTimberTable5KC2.Text);
        }

        private void UpdateTimberTable6()
        {
            decimal timberTbl6KK1 = Convert.ToDecimal(txtTimberTable6KK1.Text);
            decimal timberTbl6KK2 = Convert.ToDecimal(txtTimberTable6KK2.Text);
            decimal timberTbl6KK3 = Convert.ToDecimal(txtTimberTable6KK3.Text);
        }

        #endregion

        #region Private Methods

        #region Show/Hide Controls

        private void SetTimberEditMode(bool isEditMode, TableTypeTimber type)
        {
            if (isEditMode)
            {
                ShowHideTimberTextBoxes(type, true);
                ShowHideTimberLabels(type, false);
            }
            else
            {
                ShowHideTimberTextBoxes(type, false);
                ShowHideTimberLabels(type, true);
            }
        }

        private void ShowHideTimberTextBoxes(TableTypeTimber type, bool isShow)
        {
            switch (type)
            {
                case TableTypeTimber.Table1MaterialKwood:
                    txtTimberTable1ZAC1.Visible = isShow;
                    txtTimberTable1ZDG1.Visible = isShow;
                    txtTimberTable1ZAC2.Visible = isShow;
                    txtTimberTable1ZDG2.Visible = isShow;
                    txtTimberTable1ZAC3.Visible = isShow;
                    txtTimberTable1ZDG3.Visible = isShow;
                    txtTimberTable1ZAC4.Visible = isShow;
                    txtTimberTable1ZDG4.Visible = isShow;
                    txtTimberTable1ZAC5.Visible = isShow;
                    txtTimberTable1ZDG5.Visible = isShow;
                    txtTimberTable1ZAC6.Visible = isShow;
                    txtTimberTable1ZDG6.Visible = isShow;
                    txtTimberTable1ZAC7.Visible = isShow;
                    txtTimberTable1ZDG7.Visible = isShow;
                    txtTimberTable1ZAC8.Visible = isShow;
                    txtTimberTable1ZDG8.Visible = isShow;
                    txtTimberTable1ZAC9.Visible = isShow;
                    txtTimberTable1ZDG9.Visible = isShow;
                    txtTimberTable1ZAC10.Visible = isShow;
                    txtTimberTable1ZDG10.Visible = isShow;
                    txtTimberTable1ZAC11.Visible = isShow;
                    txtTimberTable1ZDG11.Visible = isShow;
                    txtTimberTable1ZAC12.Visible = isShow;
                    txtTimberTable1ZDG12.Visible = isShow;
                    txtTimberTable1ZAC13.Visible = isShow;
                    txtTimberTable1ZDG13.Visible = isShow;
                    txtTimberTable1ZAC14.Visible = isShow;
                    txtTimberTable1ZDG14.Visible = isShow;
                    txtTimberTable1ZAC15.Visible = isShow;
                    txtTimberTable1ZDG15.Visible = isShow;
                    txtTimberTable1ZAC16.Visible = isShow;
                    txtTimberTable1ZDG16.Visible = isShow;
                    txtTimberTable1ZAC17.Visible = isShow;
                    txtTimberTable1ZDG17.Visible = isShow;
                    txtTimberTable1ZAC18.Visible = isShow;
                    txtTimberTable1ZDG18.Visible = isShow;
                    txtTimberTable1ZAC19.Visible = isShow;
                    txtTimberTable1ZDG19.Visible = isShow;
                    break;
                case TableTypeTimber.Table2ClassSalinityKsal:
                    txtTimberTable2KZAD1.Visible = isShow;
                    txtTimberTable2KZEG1.Visible = isShow;
                    txtTimberTable2KZAD2.Visible = isShow;
                    txtTimberTable2KZEG2.Visible = isShow;
                    txtTimberTable2KZAD3.Visible = isShow;
                    txtTimberTable2KZEG3.Visible = isShow;
                    break;
                case TableTypeTimber.Table3WaterZoneKshelter:
                    txtTimberTable3KS1.Visible = isShow;
                    txtTimberTable3KS2.Visible = isShow;
                    break;
                case TableTypeTimber.Table4MaintenanceMeasureKprotext:
                    txtTimberTable4KP1.Visible = isShow;
                    txtTimberTable4KP2.Visible = isShow;
                    break;
                case TableTypeTimber.Table5ContractKcontract:
                    txtTimberTable5KC1.Visible = isShow;
                    txtTimberTable5KC2.Visible = isShow;
                    break;
                case TableTypeTimber.Table6KnotPresenceKnot:
                    txtTimberTable6KK1.Visible = isShow;
                    txtTimberTable6KK2.Visible = isShow;
                    txtTimberTable6KK3.Visible = isShow;
                    break;
            }
        }

        private void ShowHideTimberLabels(TableTypeTimber type, bool isShow)
        {
            switch (type)
            {
                case TableTypeTimber.Table1MaterialKwood:
                    lblTimberTable1ZAC1.Visible = isShow;
                    lblTimberTable1ZDG1.Visible = isShow;
                    lblTimberTable1ZAC2.Visible = isShow;
                    lblTimberTable1ZDG2.Visible = isShow;
                    lblTimberTable1ZAC3.Visible = isShow;
                    lblTimberTable1ZDG3.Visible = isShow;
                    lblTimberTable1ZAC4.Visible = isShow;
                    lblTimberTable1ZDG4.Visible = isShow;
                    lblTimberTable1ZAC5.Visible = isShow;
                    lblTimberTable1ZDG5.Visible = isShow;
                    lblTimberTable1ZAC6.Visible = isShow;
                    lblTimberTable1ZDG6.Visible = isShow;
                    lblTimberTable1ZAC7.Visible = isShow;
                    lblTimberTable1ZDG7.Visible = isShow;
                    lblTimberTable1ZAC8.Visible = isShow;
                    lblTimberTable1ZDG8.Visible = isShow;
                    lblTimberTable1ZAC9.Visible = isShow;
                    lblTimberTable1ZDG9.Visible = isShow;
                    lblTimberTable1ZAC10.Visible = isShow;
                    lblTimberTable1ZDG10.Visible = isShow;
                    lblTimberTable1ZAC11.Visible = isShow;
                    lblTimberTable1ZDG11.Visible = isShow;
                    lblTimberTable1ZAC12.Visible = isShow;
                    lblTimberTable1ZDG12.Visible = isShow;
                    lblTimberTable1ZAC13.Visible = isShow;
                    lblTimberTable1ZDG13.Visible = isShow;
                    lblTimberTable1ZAC14.Visible = isShow;
                    lblTimberTable1ZDG14.Visible = isShow;
                    lblTimberTable1ZAC15.Visible = isShow;
                    lblTimberTable1ZDG15.Visible = isShow;
                    lblTimberTable1ZAC16.Visible = isShow;
                    lblTimberTable1ZDG16.Visible = isShow;
                    lblTimberTable1ZAC17.Visible = isShow;
                    lblTimberTable1ZDG17.Visible = isShow;
                    lblTimberTable1ZAC18.Visible = isShow;
                    lblTimberTable1ZDG18.Visible = isShow;
                    lblTimberTable1ZAC19.Visible = isShow;
                    lblTimberTable1ZDG19.Visible = isShow;
                    break;
                case TableTypeTimber.Table2ClassSalinityKsal:
                    lblTimberTable2KZAD1.Visible = isShow;
                    lblTimberTable2KZEG1.Visible = isShow;
                    lblTimberTable2KZAD2.Visible = isShow;
                    lblTimberTable2KZEG2.Visible = isShow;
                    lblTimberTable2KZAD3.Visible = isShow;
                    lblTimberTable2KZEG3.Visible = isShow;
                    break;
                case TableTypeTimber.Table3WaterZoneKshelter:
                    lblTimberTable3KS1.Visible = isShow;
                    lblTimberTable3KS2.Visible = isShow;
                    break;
                case TableTypeTimber.Table4MaintenanceMeasureKprotext:
                    lblTimberTable4KP1.Visible = isShow;
                    lblTimberTable4KP2.Visible = isShow;
                    break;
                case TableTypeTimber.Table5ContractKcontract:
                    lblTimberTable5KC1.Visible = isShow;
                    lblTimberTable5KC2.Visible = isShow;
                    break;
                case TableTypeTimber.Table6KnotPresenceKnot:
                    lblTimberTable6KK1.Visible = isShow;
                    lblTimberTable6KK2.Visible = isShow;
                    lblTimberTable6KK3.Visible = isShow;
                    break;
            }
        }

        //private void HideTimberTextBoxes(TableTypeTimber type)
        //{
        //    switch (type)
        //    {
        //        case TableTypeTimber.Table1MaterialKwood:
        //            txtTimberTable1ZAC1.Visible = false;
        //            txtTimberTable1ZDG1.Visible = false;
        //            txtTimberTable1ZAC2.Visible = false;
        //            txtTimberTable1ZDG2.Visible = false;
        //            txtTimberTable1ZAC3.Visible = false;
        //            txtTimberTable1ZDG3.Visible = false;
        //            txtTimberTable1ZAC4.Visible = false;
        //            txtTimberTable1ZDG4.Visible = false;
        //            txtTimberTable1ZAC5.Visible = false;
        //            txtTimberTable1ZDG5.Visible = false;
        //            txtTimberTable1ZAC6.Visible = false;
        //            txtTimberTable1ZDG6.Visible = false;
        //            txtTimberTable1ZAC7.Visible = false;
        //            txtTimberTable1ZDG7.Visible = false;
        //            txtTimberTable1ZAC8.Visible = false;
        //            txtTimberTable1ZDG8.Visible = false;
        //            txtTimberTable1ZAC9.Visible = false;
        //            txtTimberTable1ZDG9.Visible = false;
        //            txtTimberTable1ZAC10.Visible = false;
        //            txtTimberTable1ZDG10.Visible = false;
        //            txtTimberTable1ZAC11.Visible = false;
        //            txtTimberTable1ZDG11.Visible = false;
        //            txtTimberTable1ZAC12.Visible = false;
        //            txtTimberTable1ZDG12.Visible = false;
        //            txtTimberTable1ZAC13.Visible = false;
        //            txtTimberTable1ZDG13.Visible = false;
        //            txtTimberTable1ZAC14.Visible = false;
        //            txtTimberTable1ZDG14.Visible = false;
        //            txtTimberTable1ZAC15.Visible = false;
        //            txtTimberTable1ZDG15.Visible = false;
        //            txtTimberTable1ZAC16.Visible = false;
        //            txtTimberTable1ZDG16.Visible = false;
        //            txtTimberTable1ZAC17.Visible = false;
        //            txtTimberTable1ZDG17.Visible = false;
        //            txtTimberTable1ZAC18.Visible = false;
        //            txtTimberTable1ZDG18.Visible = false;
        //            txtTimberTable1ZAC19.Visible = false;
        //            txtTimberTable1ZDG19.Visible = false;
        //            break;
        //        case TableTypeTimber.Table2ClassSalinityKsal:
        //            txtTimberTable2KZAD1.Visible = false;
        //            txtTimberTable2KZEG1.Visible = false;
        //            txtTimberTable2KZAD2.Visible = false;
        //            txtTimberTable2KZEG2.Visible = false;
        //            txtTimberTable2KZAD3.Visible = false;
        //            txtTimberTable2KZEG3.Visible = false;
        //            break;
        //        case TableTypeTimber.Table3WaterZoneKshelter:
        //            txtTimberTable3KS1.Visible = false;
        //            txtTimberTable3KS2.Visible = false;
        //            break;
        //        case TableTypeTimber.Table4MaintenanceMeasureKprotext:
        //            txtTimberTable4KP1.Visible = false;
        //            txtTimberTable4KP2.Visible = false;
        //            break;
        //        case TableTypeTimber.Table5ContractKcontract:
        //            txtTimberTable5KC1.Visible = false;
        //            txtTimberTable5KC2.Visible = false;
        //            break;
        //        case TableTypeTimber.Table6KnotPresenceKnot:
        //            txtTimberTable6KK1.Visible = false;
        //            txtTimberTable6KK2.Visible = false;
        //            txtTimberTable6KK3.Visible = false;
        //            break;
        //    }
        //}

        //private void HideTimberLabels(TableTypeTimber type)
        //{
        //    switch (type)
        //    {
        //        case TableTypeTimber.Table1MaterialKwood:
        //            lblTimberTable1ZAC1.Visible = false;
        //            lblTimberTable1ZDG1.Visible = false;
        //            lblTimberTable1ZAC2.Visible = false;
        //            lblTimberTable1ZDG2.Visible = false;
        //            lblTimberTable1ZAC3.Visible = false;
        //            lblTimberTable1ZDG3.Visible = false;
        //            lblTimberTable1ZAC4.Visible = false;
        //            lblTimberTable1ZDG4.Visible = false;
        //            lblTimberTable1ZAC5.Visible = false;
        //            lblTimberTable1ZDG5.Visible = false;
        //            lblTimberTable1ZAC6.Visible = false;
        //            lblTimberTable1ZDG6.Visible = false;
        //            lblTimberTable1ZAC7.Visible = false;
        //            lblTimberTable1ZDG7.Visible = false;
        //            lblTimberTable1ZAC8.Visible = false;
        //            lblTimberTable1ZDG8.Visible = false;
        //            lblTimberTable1ZAC9.Visible = false;
        //            lblTimberTable1ZDG9.Visible = false;
        //            lblTimberTable1ZAC10.Visible = false;
        //            lblTimberTable1ZDG10.Visible = false;
        //            lblTimberTable1ZAC11.Visible = false;
        //            lblTimberTable1ZDG11.Visible = false;
        //            lblTimberTable1ZAC12.Visible = false;
        //            lblTimberTable1ZDG12.Visible = false;
        //            lblTimberTable1ZAC13.Visible = false;
        //            lblTimberTable1ZDG13.Visible = false;
        //            lblTimberTable1ZAC14.Visible = false;
        //            lblTimberTable1ZDG14.Visible = false;
        //            lblTimberTable1ZAC15.Visible = false;
        //            lblTimberTable1ZDG15.Visible = false;
        //            lblTimberTable1ZAC16.Visible = false;
        //            lblTimberTable1ZDG16.Visible = false;
        //            lblTimberTable1ZAC17.Visible = false;
        //            lblTimberTable1ZDG17.Visible = false;
        //            lblTimberTable1ZAC18.Visible = false;
        //            lblTimberTable1ZDG18.Visible = false;
        //            lblTimberTable1ZAC19.Visible = false;
        //            lblTimberTable1ZDG19.Visible = false;
        //            break;
        //        case TableTypeTimber.Table2ClassSalinityKsal:
        //            lblTimberTable2KZAD1.Visible = false;
        //            lblTimberTable2KZEG1.Visible = false;
        //            lblTimberTable2KZAD2.Visible = false;
        //            lblTimberTable2KZEG2.Visible = false;
        //            lblTimberTable2KZAD3.Visible = false;
        //            lblTimberTable2KZEG3.Visible = false;
        //            break;
        //        case TableTypeTimber.Table3WaterZoneKshelter:
        //            lblTimberTable3KS1.Visible = false;
        //            lblTimberTable3KS2.Visible = false;
        //            break;
        //        case TableTypeTimber.Table4MaintenanceMeasureKprotext:
        //            lblTimberTable4KP1.Visible = false;
        //            lblTimberTable4KP2.Visible = false;
        //            break;
        //        case TableTypeTimber.Table5ContractKcontract:
        //            lblTimberTable5KC1.Visible = false;
        //            lblTimberTable5KC2.Visible = false;
        //            break;
        //        case TableTypeTimber.Table6KnotPresenceKnot:
        //            lblTimberTable6KK1.Visible = false;
        //            lblTimberTable6KK2.Visible = false;
        //            lblTimberTable6KK3.Visible = false;
        //            break;
        //    }
        //}

        #endregion

        #region Update Methods

        private void UpdateTable1()
        {
            CurrentTimberTemplate.isTable1Update = true;
            CurrentTimberTemplate.valTbl1HWZAC1 = Convert.ToDecimal(txtTimberTable1ZAC1.Text);
            CurrentTimberTemplate.valTbl1HWZDG1 = Convert.ToDecimal(txtTimberTable1ZDG1.Text);
            CurrentTimberTemplate.valTbl1HWZAC2 = Convert.ToDecimal(txtTimberTable1ZAC2.Text);
            CurrentTimberTemplate.valTbl1HWZDG2 = Convert.ToDecimal(txtTimberTable1ZDG2.Text);
            CurrentTimberTemplate.valTbl1HWZAC3 = Convert.ToDecimal(txtTimberTable1ZAC3.Text);
            CurrentTimberTemplate.valTbl1HWZDG3 = Convert.ToDecimal(txtTimberTable1ZDG3.Text);
            CurrentTimberTemplate.valTbl1HWZAC4 = Convert.ToDecimal(txtTimberTable1ZAC4.Text);
            CurrentTimberTemplate.valTbl1HWZDG4 = Convert.ToDecimal(txtTimberTable1ZDG4.Text);
            CurrentTimberTemplate.valTbl1SPSUZAC = Convert.ToDecimal(txtTimberTable1ZAC5.Text);
            CurrentTimberTemplate.valTbl1SPSUZDG = Convert.ToDecimal(txtTimberTable1ZDG5.Text);
            CurrentTimberTemplate.valTbl1SPSCRZAC1 = Convert.ToDecimal(txtTimberTable1ZAC6.Text);
            CurrentTimberTemplate.valTbl1SPSCRZDG1 = Convert.ToDecimal(txtTimberTable1ZDG6.Text);
            CurrentTimberTemplate.valTbl1SPSCRZAC2 = Convert.ToDecimal(txtTimberTable1ZAC7.Text);
            CurrentTimberTemplate.valTbl1SPSCRZDG2 = Convert.ToDecimal(txtTimberTable1ZDG7.Text);
            CurrentTimberTemplate.valTbl1SPSCCZAC1 = Convert.ToDecimal(txtTimberTable1ZAC8.Text);
            CurrentTimberTemplate.valTbl1SPSCCZDG1 = Convert.ToDecimal(txtTimberTable1ZDG8.Text);
            CurrentTimberTemplate.valTbl1SPSCCZAC2 = Convert.ToDecimal(txtTimberTable1ZAC9.Text);
            CurrentTimberTemplate.valTbl1SPSCCZDG2 = Convert.ToDecimal(txtTimberTable1ZDG9.Text);
            CurrentTimberTemplate.valTbl1SPSCCZAC3 = Convert.ToDecimal(txtTimberTable1ZAC10.Text);
            CurrentTimberTemplate.valTbl1SPSCCZDG3 = Convert.ToDecimal(txtTimberTable1ZDG10.Text);
            CurrentTimberTemplate.valTbl1SPSCCZAC4 = Convert.ToDecimal(txtTimberTable1ZAC11.Text);
            CurrentTimberTemplate.valTbl1SPSCCZDG4 = Convert.ToDecimal(txtTimberTable1ZDG11.Text);
            CurrentTimberTemplate.valTbl1SPSDBTZAC = Convert.ToDecimal(txtTimberTable1ZAC12.Text);
            CurrentTimberTemplate.valTbl1SPSDBTZDG = Convert.ToDecimal(txtTimberTable1ZDG12.Text);
            CurrentTimberTemplate.valTbl1SPHUZAC = Convert.ToDecimal(txtTimberTable1ZAC13.Text);
            CurrentTimberTemplate.valTbl1SPHUZDG = Convert.ToDecimal(txtTimberTable1ZDG13.Text);
            CurrentTimberTemplate.valTbl1SPHCRZAC1 = Convert.ToDecimal(txtTimberTable1ZAC14.Text);
            CurrentTimberTemplate.valTbl1SPHCRZDG1 = Convert.ToDecimal(txtTimberTable1ZDG14.Text);
            CurrentTimberTemplate.valTbl1SPHCRZAC2 = Convert.ToDecimal(txtTimberTable1ZAC15.Text);
            CurrentTimberTemplate.valTbl1SPHCRZDG2 = Convert.ToDecimal(txtTimberTable1ZDG15.Text);
            CurrentTimberTemplate.valTbl1SPHCCZAC1 = Convert.ToDecimal(txtTimberTable1ZAC16.Text);
            CurrentTimberTemplate.valTbl1SPHCCZDG1 = Convert.ToDecimal(txtTimberTable1ZDG16.Text);
            CurrentTimberTemplate.valTbl1SPHCCZAC2 = Convert.ToDecimal(txtTimberTable1ZAC17.Text);
            CurrentTimberTemplate.valTbl1SPHCCZDG2 = Convert.ToDecimal(txtTimberTable1ZDG17.Text);
            CurrentTimberTemplate.valTbl1SPHCCZAC3 = Convert.ToDecimal(txtTimberTable1ZAC18.Text);
            CurrentTimberTemplate.valTbl1SPHCCZDG3 = Convert.ToDecimal(txtTimberTable1ZDG18.Text);
            CurrentTimberTemplate.valTbl1SPHDBTZAC = Convert.ToDecimal(txtTimberTable1ZAC19.Text);
            CurrentTimberTemplate.valTbl1SPHDBTZDG = Convert.ToDecimal(txtTimberTable1ZAC19.Text);
        }

        private void UpdateTable2()
        {
            CurrentTimberTemplate.isTable2Update = true;
            CurrentTimberTemplate.valTbl2Class1ZAD = Convert.ToDecimal(txtTimberTable2KZAD1.Text);
            CurrentTimberTemplate.valTbl2Class1ZEG = Convert.ToDecimal(txtTimberTable2KZEG1.Text);
            CurrentTimberTemplate.valTbl2Class2ZAD = Convert.ToDecimal(txtTimberTable2KZAD2.Text);
            CurrentTimberTemplate.valTbl2Class2ZEG = Convert.ToDecimal(txtTimberTable2KZEG2.Text);
            CurrentTimberTemplate.valTbl2Class3ZAD = Convert.ToDecimal(txtTimberTable2KZAD3.Text);
            CurrentTimberTemplate.valTbl2Class3ZEG = Convert.ToDecimal(txtTimberTable2KZEG3.Text);
        }

        private void UpdateTable3()
        {
            CurrentTimberTemplate.isTable3Update = true;
            CurrentTimberTemplate.valTbl3Exp1 = Convert.ToDecimal(txtTimberTable3KS1.Text);
            CurrentTimberTemplate.valTbl3Exp2 = Convert.ToDecimal(txtTimberTable3KS2.Text);
        }

        private void UpdateTable4()
        {
            CurrentTimberTemplate.isTable4Update = true;
            CurrentTimberTemplate.valTbl4Mtn1 = Convert.ToDecimal(txtTimberTable4KP1.Text);
            CurrentTimberTemplate.valTbl4Mtn2 = Convert.ToDecimal(txtTimberTable4KP2.Text);
        }

        private void UpdateTable5()
        {
            CurrentTimberTemplate.isTable5Update = true;
            CurrentTimberTemplate.valTbl5Cnt1 = Convert.ToDecimal(txtTimberTable5KC1.Text);
            CurrentTimberTemplate.valTbl5Cnt2 = Convert.ToDecimal(txtTimberTable5KC2.Text);
        }

        private void UpdateTable6()
        {
            CurrentTimberTemplate.isTable6Update = true;
            CurrentTimberTemplate.valTbl6Knt1 = Convert.ToDecimal(txtTimberTable6KK1.Text);
            CurrentTimberTemplate.valTbl6Knt2 = Convert.ToDecimal(txtTimberTable6KK2.Text);
            CurrentTimberTemplate.valTbl6Knt3 = Convert.ToDecimal(txtTimberTable6KK3.Text);
        }

        #endregion

        private void EnableTimberEditMode(bool isEditMode)
        {
            SetTimberEditMode(isEditMode, TableTypeTimber.Table1MaterialKwood);
            SetTimberEditMode(isEditMode, TableTypeTimber.Table2ClassSalinityKsal);
            SetTimberEditMode(isEditMode, TableTypeTimber.Table3WaterZoneKshelter);
            SetTimberEditMode(isEditMode, TableTypeTimber.Table4MaintenanceMeasureKprotext);
            SetTimberEditMode(isEditMode, TableTypeTimber.Table5ContractKcontract);
            SetTimberEditMode(isEditMode, TableTypeTimber.Table6KnotPresenceKnot);
        }

        #endregion

        #region Public Methods

        public void LoadDefaultTimberLabelValues(TimberTemplate currentTemplate, TableTypeTimber type, bool isAll)
        {
            if (type == TableTypeTimber.Table1MaterialKwood || isAll)
            {
                lblTimberTable1ZAC1.Text = Convert.ToString(currentTemplate.valTbl1HWZAC1);
                lblTimberTable1ZDG1.Text = Convert.ToString(currentTemplate.valTbl1HWZDG1);
                lblTimberTable1ZAC2.Text = Convert.ToString(currentTemplate.valTbl1HWZAC2);
                lblTimberTable1ZDG2.Text = Convert.ToString(currentTemplate.valTbl1HWZDG2);
                lblTimberTable1ZAC3.Text = Convert.ToString(currentTemplate.valTbl1HWZAC3);
                lblTimberTable1ZDG3.Text = Convert.ToString(currentTemplate.valTbl1HWZDG3);
                lblTimberTable1ZAC4.Text = Convert.ToString(currentTemplate.valTbl1HWZAC4);
                lblTimberTable1ZDG4.Text = Convert.ToString(currentTemplate.valTbl1HWZDG4);
                lblTimberTable1ZAC5.Text = Convert.ToString(currentTemplate.valTbl1SPSUZAC);
                lblTimberTable1ZDG5.Text = Convert.ToString(currentTemplate.valTbl1SPSUZDG);
                lblTimberTable1ZAC6.Text = Convert.ToString(currentTemplate.valTbl1SPSCRZAC1);
                lblTimberTable1ZDG6.Text = Convert.ToString(currentTemplate.valTbl1SPSCRZDG1);
                lblTimberTable1ZAC7.Text = Convert.ToString(currentTemplate.valTbl1SPSCRZAC2);
                lblTimberTable1ZDG7.Text = Convert.ToString(currentTemplate.valTbl1SPSCRZDG2);
                lblTimberTable1ZAC8.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZAC1);
                lblTimberTable1ZDG8.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZDG1);
                lblTimberTable1ZAC9.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZAC2);
                lblTimberTable1ZDG9.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZDG2);
                lblTimberTable1ZAC10.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZAC3);
                lblTimberTable1ZDG10.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZDG3);
                lblTimberTable1ZAC11.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZAC4);
                lblTimberTable1ZDG11.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZDG4);
                lblTimberTable1ZAC12.Text = Convert.ToString(currentTemplate.valTbl1SPSDBTZAC);
                lblTimberTable1ZDG12.Text = Convert.ToString(currentTemplate.valTbl1SPSDBTZDG);
                lblTimberTable1ZAC13.Text = Convert.ToString(currentTemplate.valTbl1SPHUZAC);
                lblTimberTable1ZDG13.Text = Convert.ToString(currentTemplate.valTbl1SPHUZDG);
                lblTimberTable1ZAC14.Text = Convert.ToString(currentTemplate.valTbl1SPHCRZAC1);
                lblTimberTable1ZDG14.Text = Convert.ToString(currentTemplate.valTbl1SPHCRZDG1);
                lblTimberTable1ZAC15.Text = Convert.ToString(currentTemplate.valTbl1SPHCRZAC2);
                lblTimberTable1ZDG15.Text = Convert.ToString(currentTemplate.valTbl1SPHCRZDG2);
                lblTimberTable1ZAC16.Text = Convert.ToString(currentTemplate.valTbl1SPHCCZAC1);
                lblTimberTable1ZDG16.Text = Convert.ToString(currentTemplate.valTbl1SPHCCZDG1);
                lblTimberTable1ZAC17.Text = Convert.ToString(currentTemplate.valTbl1SPHCCZAC2);
                lblTimberTable1ZDG17.Text = Convert.ToString(currentTemplate.valTbl1SPHCCZDG2);
                lblTimberTable1ZAC18.Text = Convert.ToString(currentTemplate.valTbl1SPHCCZAC3);
                lblTimberTable1ZDG18.Text = Convert.ToString(currentTemplate.valTbl1SPHCCZDG3);
                lblTimberTable1ZAC19.Text = Convert.ToString(currentTemplate.valTbl1SPHDBTZAC);
                lblTimberTable1ZDG19.Text = Convert.ToString(currentTemplate.valTbl1SPHDBTZDG);
            }
            if (type == TableTypeTimber.Table2ClassSalinityKsal || isAll)
            {
                lblTimberTable2KZAD1.Text = Convert.ToString(currentTemplate.valTbl2Class1ZAD);
                lblTimberTable2KZEG1.Text = Convert.ToString(currentTemplate.valTbl2Class1ZEG);
                lblTimberTable2KZAD2.Text = Convert.ToString(currentTemplate.valTbl2Class2ZAD);
                lblTimberTable2KZEG2.Text = Convert.ToString(currentTemplate.valTbl2Class2ZEG);
                lblTimberTable2KZAD3.Text = Convert.ToString(currentTemplate.valTbl2Class3ZAD);
                lblTimberTable2KZEG3.Text = Convert.ToString(currentTemplate.valTbl2Class3ZEG);
            }
            if (type == TableTypeTimber.Table3WaterZoneKshelter || isAll)
            {
                lblTimberTable3KS1.Text = Convert.ToString(currentTemplate.valTbl3Exp1);
                lblTimberTable3KS2.Text = Convert.ToString(currentTemplate.valTbl3Exp2);
            }
            if (type == TableTypeTimber.Table4MaintenanceMeasureKprotext || isAll)
            {
                lblTimberTable4KP1.Text = Convert.ToString(currentTemplate.valTbl4Mtn1);
                lblTimberTable4KP2.Text = Convert.ToString(currentTemplate.valTbl4Mtn2);
            }
            if (type == TableTypeTimber.Table5ContractKcontract || isAll)
            {
                lblTimberTable5KC1.Text = Convert.ToString(currentTemplate.valTbl5Cnt1);
                lblTimberTable5KC2.Text = Convert.ToString(currentTemplate.valTbl5Cnt2);
            }
            if (type == TableTypeTimber.Table6KnotPresenceKnot || isAll)
            {
                lblTimberTable6KK1.Text = Convert.ToString(currentTemplate.valTbl6Knt1);
                lblTimberTable6KK2.Text = Convert.ToString(currentTemplate.valTbl6Knt2);
                lblTimberTable6KK3.Text = Convert.ToString(currentTemplate.valTbl6Knt3);
            }

            UserSelection.TimberTemplate = currentTemplate;
        }

        public void LoadDefaultTimberTextValues(TableTypeTimber type, bool isAll)
        {
            TimberTemplate currentTemplate = CurrentTimberTemplate;

            if (type == TableTypeTimber.Table1MaterialKwood || isAll)
            {
                txtTimberTable1ZAC1.Text = Convert.ToString(currentTemplate.valTbl1HWZAC1);
                txtTimberTable1ZDG1.Text = Convert.ToString(currentTemplate.valTbl1HWZDG1);
                txtTimberTable1ZAC2.Text = Convert.ToString(currentTemplate.valTbl1HWZAC2);
                txtTimberTable1ZDG2.Text = Convert.ToString(currentTemplate.valTbl1HWZDG2);
                txtTimberTable1ZAC3.Text = Convert.ToString(currentTemplate.valTbl1HWZAC3);
                txtTimberTable1ZDG3.Text = Convert.ToString(currentTemplate.valTbl1HWZDG3);
                txtTimberTable1ZAC4.Text = Convert.ToString(currentTemplate.valTbl1HWZAC4);
                txtTimberTable1ZDG4.Text = Convert.ToString(currentTemplate.valTbl1HWZDG4);
                txtTimberTable1ZAC5.Text = Convert.ToString(currentTemplate.valTbl1SPSUZAC);
                txtTimberTable1ZDG5.Text = Convert.ToString(currentTemplate.valTbl1SPSUZDG);
                txtTimberTable1ZAC6.Text = Convert.ToString(currentTemplate.valTbl1SPSCRZAC1);
                txtTimberTable1ZDG6.Text = Convert.ToString(currentTemplate.valTbl1SPSCRZDG1);
                txtTimberTable1ZAC7.Text = Convert.ToString(currentTemplate.valTbl1SPSCRZAC2);
                txtTimberTable1ZDG7.Text = Convert.ToString(currentTemplate.valTbl1SPSCRZDG2);
                txtTimberTable1ZAC8.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZAC1);
                txtTimberTable1ZDG8.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZDG1);
                txtTimberTable1ZAC9.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZAC2);
                txtTimberTable1ZDG9.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZDG2);
                txtTimberTable1ZAC10.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZAC3);
                txtTimberTable1ZDG10.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZDG3);
                txtTimberTable1ZAC11.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZAC4);
                txtTimberTable1ZDG11.Text = Convert.ToString(currentTemplate.valTbl1SPSCCZDG4);
                txtTimberTable1ZAC12.Text = Convert.ToString(currentTemplate.valTbl1SPSDBTZAC);
                txtTimberTable1ZDG12.Text = Convert.ToString(currentTemplate.valTbl1SPSDBTZDG);
                txtTimberTable1ZAC13.Text = Convert.ToString(currentTemplate.valTbl1SPHUZAC);
                txtTimberTable1ZDG13.Text = Convert.ToString(currentTemplate.valTbl1SPHUZDG);
                txtTimberTable1ZAC14.Text = Convert.ToString(currentTemplate.valTbl1SPHCRZAC1);
                txtTimberTable1ZDG14.Text = Convert.ToString(currentTemplate.valTbl1SPHCRZDG1);
                txtTimberTable1ZAC15.Text = Convert.ToString(currentTemplate.valTbl1SPHCRZAC2);
                txtTimberTable1ZDG15.Text = Convert.ToString(currentTemplate.valTbl1SPHCRZDG2);
                txtTimberTable1ZAC16.Text = Convert.ToString(currentTemplate.valTbl1SPHCCZAC1);
                txtTimberTable1ZDG16.Text = Convert.ToString(currentTemplate.valTbl1SPHCCZDG1);
                txtTimberTable1ZAC17.Text = Convert.ToString(currentTemplate.valTbl1SPHCCZAC2);
                txtTimberTable1ZDG17.Text = Convert.ToString(currentTemplate.valTbl1SPHCCZDG2);
                txtTimberTable1ZAC18.Text = Convert.ToString(currentTemplate.valTbl1SPHCCZAC3);
                txtTimberTable1ZDG18.Text = Convert.ToString(currentTemplate.valTbl1SPHCCZDG3);
                txtTimberTable1ZAC19.Text = Convert.ToString(currentTemplate.valTbl1SPHDBTZAC);
                txtTimberTable1ZDG19.Text = Convert.ToString(currentTemplate.valTbl1SPHDBTZDG);
            }
            if (type == TableTypeTimber.Table2ClassSalinityKsal || isAll)
            {
                txtTimberTable2KZAD1.Text = Convert.ToString(currentTemplate.valTbl2Class1ZAD);
                txtTimberTable2KZEG1.Text = Convert.ToString(currentTemplate.valTbl2Class1ZEG);
                txtTimberTable2KZAD2.Text = Convert.ToString(currentTemplate.valTbl2Class2ZAD);
                txtTimberTable2KZEG2.Text = Convert.ToString(currentTemplate.valTbl2Class2ZEG);
                txtTimberTable2KZAD3.Text = Convert.ToString(currentTemplate.valTbl2Class3ZAD);
                txtTimberTable2KZEG3.Text = Convert.ToString(currentTemplate.valTbl2Class3ZEG);
            }
            if (type == TableTypeTimber.Table3WaterZoneKshelter || isAll)
            {
                txtTimberTable3KS1.Text = Convert.ToString(currentTemplate.valTbl3Exp1);
                txtTimberTable3KS2.Text = Convert.ToString(currentTemplate.valTbl3Exp2);
            }
            if (type == TableTypeTimber.Table4MaintenanceMeasureKprotext || isAll)
            {
                txtTimberTable4KP1.Text = Convert.ToString(currentTemplate.valTbl4Mtn1);
                txtTimberTable4KP2.Text = Convert.ToString(currentTemplate.valTbl4Mtn2);
            }
            if (type == TableTypeTimber.Table5ContractKcontract || isAll)
            {
                txtTimberTable5KC1.Text = Convert.ToString(currentTemplate.valTbl5Cnt1);
                txtTimberTable5KC2.Text = Convert.ToString(currentTemplate.valTbl5Cnt2);
            }
            if (type == TableTypeTimber.Table6KnotPresenceKnot || isAll)
            {
                txtTimberTable6KK1.Text = Convert.ToString(currentTemplate.valTbl6Knt1);
                txtTimberTable6KK2.Text = Convert.ToString(currentTemplate.valTbl6Knt2);
                txtTimberTable6KK3.Text = Convert.ToString(currentTemplate.valTbl6Knt3);
            }
        }

        #endregion

    }
}