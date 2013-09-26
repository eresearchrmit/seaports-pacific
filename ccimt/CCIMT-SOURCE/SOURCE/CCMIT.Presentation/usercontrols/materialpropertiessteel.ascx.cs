using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CCIMT.Presentation.usercontrols
{
    public partial class materialpropertiessteel : BaseControl
    {

        #region Page Events

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                EnableSteelEditMode(false);
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

        protected void btnNORDEdit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnNORDEdit.Text, Common.EVENT_NAME_EDIT))
            {
                SetSteelEditMode(true, TableTypeSteel.NoOfRainDays);
                LoadDefaultSteelTextValues(TableTypeSteel.NoOfRainDays, false);
                btnNORDEdit.Text = Common.EVENT_NAME_SAVE;
                btnNORDEdit.ValidationGroup = "NORD";
                btnNORDCancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
                    UpdateNoOfRainDays();

                SetSteelEditMode(false, TableTypeSteel.NoOfRainDays);
                SteelTemplate currentTemplate = CurrentSteelTemplate;
                LoadDefaultSteelLabelValues(currentTemplate, TableTypeSteel.NoOfRainDays, false);
                btnNORDEdit.Text = Common.EVENT_NAME_EDIT;
                btnNORDEdit.ValidationGroup = String.Empty;
                btnNORDCancel.Visible = false;
            }
        }

        protected void btnNORDCancel_Click(object sender, EventArgs e)
        {
            SetSteelEditMode(false, TableTypeSteel.NoOfRainDays);
            SteelTemplate currentTemplate = CurrentSteelTemplate;
            LoadDefaultSteelLabelValues(currentTemplate, TableTypeSteel.NoOfRainDays, false);
            btnNORDEdit.Text = Common.EVENT_NAME_EDIT;
            btnNORDEdit.ValidationGroup = String.Empty;
            btnNORDCancel.Visible = false;
        }

        protected void btnZFEdit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnZFEdit.Text, Common.EVENT_NAME_EDIT))
            {
                SetSteelEditMode(true, TableTypeSteel.ZoneFactor);
                LoadDefaultSteelTextValues(TableTypeSteel.ZoneFactor, false);
                btnZFEdit.Text = Common.EVENT_NAME_SAVE;
                btnZFEdit.ValidationGroup = "ZF";
                btnZFCancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
                    UpdateZoneFactor();

                SetSteelEditMode(false, TableTypeSteel.ZoneFactor);
                SteelTemplate currentTemplate = CurrentSteelTemplate;
                LoadDefaultSteelLabelValues(currentTemplate, TableTypeSteel.ZoneFactor, false);
                btnZFEdit.Text = Common.EVENT_NAME_EDIT;
                btnZFEdit.ValidationGroup = String.Empty;
                btnZFCancel.Visible = false;
            }
        }

        protected void btnZFCancel_Click(object sender, EventArgs e)
        {
            SetSteelEditMode(false, TableTypeSteel.ZoneFactor);
            SteelTemplate currentTemplate = CurrentSteelTemplate;
            LoadDefaultSteelLabelValues(currentTemplate, TableTypeSteel.ZoneFactor, false);
            btnZFEdit.Text = Common.EVENT_NAME_EDIT;
            btnZFEdit.ValidationGroup = String.Empty;
            btnZFCancel.Visible = false;
        }

        protected void btnCEEdit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnCEEdit.Text, Common.EVENT_NAME_EDIT))
            {
                SetSteelEditMode(true, TableTypeSteel.CoastalExposure);
                LoadDefaultSteelTextValues(TableTypeSteel.CoastalExposure, false);
                btnCEEdit.Text = Common.EVENT_NAME_SAVE;
                btnCEEdit.ValidationGroup = "CE";
                btnCECancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
                    UpdateCoastalExposure();

                SetSteelEditMode(false, TableTypeSteel.CoastalExposure);
                SteelTemplate currentTemplate = CurrentSteelTemplate;
                LoadDefaultSteelLabelValues(currentTemplate, TableTypeSteel.CoastalExposure, false);
                btnCEEdit.Text = Common.EVENT_NAME_EDIT;
                btnCEEdit.ValidationGroup = String.Empty;
                btnCECancel.Visible = false;
            }
        }

        protected void btnCECancel_Click(object sender, EventArgs e)
        {
            SetSteelEditMode(false, TableTypeSteel.CoastalExposure);
            SteelTemplate currentTemplate = CurrentSteelTemplate;
            LoadDefaultSteelLabelValues(currentTemplate, TableTypeSteel.CoastalExposure, false);
            btnCEEdit.Text = Common.EVENT_NAME_EDIT;
            btnCEEdit.ValidationGroup = String.Empty;
            btnCECancel.Visible = false;
        }

        protected void btnSCEdit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnSCEdit.Text, Common.EVENT_NAME_EDIT))
            {
                SetSteelEditMode(true, TableTypeSteel.SiteClassification);
                LoadDefaultSteelTextValues(TableTypeSteel.SiteClassification, false);
                btnSCEdit.Text = Common.EVENT_NAME_SAVE;
                btnSCEdit.ValidationGroup = "SC";
                btnSCCancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
                    UpdateSiteClassification();

                SetSteelEditMode(false, TableTypeSteel.SiteClassification);
                SteelTemplate currentTemplate = CurrentSteelTemplate;
                LoadDefaultSteelLabelValues(currentTemplate, TableTypeSteel.SiteClassification, false);
                btnSCEdit.Text = Common.EVENT_NAME_EDIT;
                btnSCEdit.ValidationGroup = String.Empty;
                btnSCCancel.Visible = false;
            }
        }

        protected void btnSCCancel_Click(object sender, EventArgs e)
        {
            SetSteelEditMode(false, TableTypeSteel.SiteClassification);
            SteelTemplate currentTemplate = CurrentSteelTemplate;
            LoadDefaultSteelLabelValues(currentTemplate, TableTypeSteel.SiteClassification, false);
            btnSCEdit.Text = Common.EVENT_NAME_EDIT;
            btnSCEdit.ValidationGroup = String.Empty;
            btnSCCancel.Visible = false;
        }

        #endregion

        #region Update Methods

        private void UpdateSteelNoOfRainDays()
        {
            decimal noOfRDZA = Convert.ToDecimal(txtNORDA.Text);
            decimal noOfRDZB = Convert.ToDecimal(txtNORDB.Text);
            decimal noOfRDZC = Convert.ToDecimal(txtNORDC.Text);
            decimal noOfRDZD = Convert.ToDecimal(txtNORDD.Text);
            decimal noOfRDZE = Convert.ToDecimal(txtNORDE.Text);
        }

        private void UpdateSteelZoneFactors()
        {
            decimal zoneFactAlphaCoast1 = Convert.ToDecimal(txtZFAC1.Text);
            decimal zoneFactAlphaOcean1 = Convert.ToDecimal(txtZFAO1.Text);
            decimal zoneFactAlphaCoast2 = Convert.ToDecimal(txtZFAC2.Text);
            decimal zoneFactAlphaOcean2 = Convert.ToDecimal(txtZFAO2.Text);
            decimal zoneFactAlphaCoast3 = Convert.ToDecimal(txtZFAC3.Text);
            decimal zoneFactAlphaOcean3 = Convert.ToDecimal(txtZFAO3.Text);
        }

        private void UpdateSteelCoastalExposure()
        {
            decimal coExpBeataCoast1 = Convert.ToDecimal(txtCEBCExp1.Text);
            decimal coExpBeataCoast2 = Convert.ToDecimal(txtCEBCExp2.Text);
            decimal coExpBeataCoast3 = Convert.ToDecimal(txtCEBCExp3.Text);
            decimal coExpBeataCoast4 = Convert.ToDecimal(txtCEBCExp4.Text);
        }

        private void UpdateSteelSiteClassification()
        {
            decimal siteClassAlphaExpo1 = Convert.ToDecimal(txtSCSite1.Text);
            decimal siteClassAlphaExpo2 = Convert.ToDecimal(txtSCSite2.Text);
            decimal siteClassAlphaExpo3 = Convert.ToDecimal(txtSCSite3.Text);
            decimal siteClassAlphaExpo4 = Convert.ToDecimal(txtSCSite4.Text);
        }

        #endregion

        #region Private Methods

        #region Show/Hide Controls

        private void SetSteelEditMode(bool isEditMode, TableTypeSteel type)
        {
            if (isEditMode)
            {
                ShowHideSteelTextBoxes(type, true);
                ShowHideSteelLabels(type, false);
            }
            else
            {
                ShowHideSteelTextBoxes(type, false);
                ShowHideSteelLabels(type, true);
            }
        }

        private void ShowHideSteelTextBoxes(TableTypeSteel type, bool isShow)
        {
            switch (type)
            {
                case TableTypeSteel.NoOfRainDays:
                    txtNORDA.Visible = isShow;
                    txtNORDB.Visible = isShow;
                    txtNORDC.Visible = isShow;
                    txtNORDD.Visible = isShow;
                    txtNORDE.Visible = isShow;
                    break;
                case TableTypeSteel.ZoneFactor:
                    txtZFAC1.Visible = isShow;
                    txtZFAO1.Visible = isShow;
                    txtZFAC2.Visible = isShow;
                    txtZFAO2.Visible = isShow;
                    txtZFAC3.Visible = isShow;
                    txtZFAO3.Visible = isShow;  
                    break;
                case TableTypeSteel.CoastalExposure:
                    txtCEBCExp1.Visible = isShow;
                    txtCEBCExp2.Visible = isShow;
                    txtCEBCExp3.Visible = isShow;
                    txtCEBCExp4.Visible = isShow;
                    break;
                case TableTypeSteel.SiteClassification:
                    txtSCSite1.Visible = isShow;
                    txtSCSite2.Visible = isShow;
                    txtSCSite3.Visible = isShow;
                    txtSCSite4.Visible = isShow;
                    break;                
            }
        }

        private void ShowHideSteelLabels(TableTypeSteel type, bool isShow)
        {
            switch (type)
            {
                case TableTypeSteel.NoOfRainDays:
                    lblNORDA.Visible = isShow;
                    lblNORDB.Visible = isShow;
                    lblNORDC.Visible = isShow;
                    lblNORDD.Visible = isShow;
                    lblNORDE.Visible = isShow;
                    break;
                case TableTypeSteel.ZoneFactor:
                    lblZFAC1.Visible = isShow;
                    lblZFAO1.Visible = isShow;
                    lblZFAC2.Visible = isShow;
                    lblZFAO2.Visible = isShow;
                    lblZFAC3.Visible = isShow;
                    lblZFAO3.Visible = isShow;
                    break;
                case TableTypeSteel.CoastalExposure:
                    lblCEBCExp1.Visible = isShow;
                    lblCEBCExp2.Visible = isShow;
                    lblCEBCExp3.Visible = isShow;
                    lblCEBCExp4.Visible = isShow;
                    break;
                case TableTypeSteel.SiteClassification:
                    lblSCSite1.Visible = isShow;
                    lblSCSite2.Visible = isShow;
                    lblSCSite3.Visible = isShow;
                    lblSCSite4.Visible = isShow;
                    break; 
            }
        }

        #endregion

        #region Update Methods

        private void UpdateNoOfRainDays()
        {
            CurrentSteelTemplate.isNoOfRDUpdated = true;
            CurrentSteelTemplate.NORDA = Convert.ToInt32(txtNORDA.Text);
            CurrentSteelTemplate.NORDB = Convert.ToInt32(txtNORDB.Text);
            CurrentSteelTemplate.NORDC = Convert.ToInt32(txtNORDC.Text);
            CurrentSteelTemplate.NORDD = Convert.ToInt32(txtNORDD.Text);
            CurrentSteelTemplate.NORDE = Convert.ToInt32(txtNORDE.Text);
        }

        private void UpdateZoneFactor()
        {
            CurrentSteelTemplate.isZoneFactorUpdated = true;
            CurrentSteelTemplate.ZFAC1 = Convert.ToInt32(txtZFAC1.Text);
            CurrentSteelTemplate.ZFAO1 = Convert.ToInt32(txtZFAO1.Text);
            CurrentSteelTemplate.ZFAC2 = Convert.ToInt32(txtZFAC2.Text);
            CurrentSteelTemplate.ZFAO2 = Convert.ToInt32(txtZFAO2.Text);
            CurrentSteelTemplate.ZFAC3 = Convert.ToInt32(txtZFAC3.Text);
            CurrentSteelTemplate.ZFAO3 = Convert.ToInt32(txtZFAO3.Text);
        }

        private void UpdateCoastalExposure()
        {
            CurrentSteelTemplate.isCoastalExposureUpdated = true;
            CurrentSteelTemplate.CEBCExp1 = Convert.ToDecimal(txtCEBCExp1.Text);
            CurrentSteelTemplate.CEBCExp2 = Convert.ToDecimal(txtCEBCExp2.Text);
            CurrentSteelTemplate.CEBCExp3 = Convert.ToDecimal(txtCEBCExp3.Text);
            CurrentSteelTemplate.CEBCExp4 = Convert.ToDecimal(txtCEBCExp4.Text);
        }

        private void UpdateSiteClassification()
        {
            CurrentSteelTemplate.isSiteClassificationUpdated = true;
            CurrentSteelTemplate.SCSite1 = Convert.ToDecimal(txtSCSite1.Text);
            CurrentSteelTemplate.SCSite2 = Convert.ToDecimal(txtSCSite2.Text);
            CurrentSteelTemplate.SCSite3 = Convert.ToDecimal(txtSCSite3.Text);
            CurrentSteelTemplate.SCSite4 = Convert.ToDecimal(txtSCSite4.Text);
        }

        #endregion

        private void EnableSteelEditMode(bool isEditMode)
        {
            SetSteelEditMode(isEditMode, TableTypeSteel.NoOfRainDays);
            SetSteelEditMode(isEditMode, TableTypeSteel.ZoneFactor);
            SetSteelEditMode(isEditMode, TableTypeSteel.CoastalExposure);
            SetSteelEditMode(isEditMode, TableTypeSteel.SiteClassification);
        }

        #endregion

        #region Public Methods

        public void LoadDefaultSteelLabelValues(SteelTemplate currentTemplate, TableTypeSteel type, bool isAll)
        {
            if (type == TableTypeSteel.NoOfRainDays || isAll)
            {
                lblNORDA.Text = Convert.ToString(currentTemplate.NORDA);
                lblNORDB.Text = Convert.ToString(currentTemplate.NORDB);
                lblNORDC.Text = Convert.ToString(currentTemplate.NORDC);
                lblNORDD.Text = Convert.ToString(currentTemplate.NORDD);
                lblNORDE.Text = Convert.ToString(currentTemplate.NORDE);
            }
            if (type == TableTypeSteel.ZoneFactor || isAll)
            {
                lblZFAC1.Text = Convert.ToString(currentTemplate.ZFAC1);
                lblZFAO1.Text = Convert.ToString(currentTemplate.ZFAO1);
                lblZFAC2.Text = Convert.ToString(currentTemplate.ZFAC2);
                lblZFAO2.Text = Convert.ToString(currentTemplate.ZFAO2);
                lblZFAC3.Text = Convert.ToString(currentTemplate.ZFAC3);
                lblZFAO3.Text = Convert.ToString(currentTemplate.ZFAO3);
                
            }
            if (type == TableTypeSteel.CoastalExposure || isAll)
            {
                lblCEBCExp1.Text = Convert.ToString(currentTemplate.CEBCExp1);
                lblCEBCExp2.Text = Convert.ToString(currentTemplate.CEBCExp2);
                lblCEBCExp3.Text = Convert.ToString(currentTemplate.CEBCExp3);
                lblCEBCExp4.Text = Convert.ToString(currentTemplate.CEBCExp4);
            }
            if (type == TableTypeSteel.SiteClassification || isAll)
            {
                lblSCSite1.Text = Convert.ToString(currentTemplate.SCSite1);
                lblSCSite2.Text = Convert.ToString(currentTemplate.SCSite2);
                lblSCSite3.Text = Convert.ToString(currentTemplate.SCSite3);
                lblSCSite4.Text = Convert.ToString(currentTemplate.SCSite4);
            }

            UserSelection.SteelTemplate = currentTemplate;
        }

        public void LoadDefaultSteelTextValues(TableTypeSteel type, bool isAll)
        {
            SteelTemplate currentTemplate = CurrentSteelTemplate;

            if (type == TableTypeSteel.NoOfRainDays || isAll)
            {                
                txtNORDA.Text = Convert.ToString(currentTemplate.NORDA);
                txtNORDB.Text = Convert.ToString(currentTemplate.NORDB);
                txtNORDC.Text = Convert.ToString(currentTemplate.NORDC);
                txtNORDD.Text = Convert.ToString(currentTemplate.NORDD);
                txtNORDE.Text = Convert.ToString(currentTemplate.NORDE);
            }
            if (type == TableTypeSteel.ZoneFactor || isAll)
            {                
                txtZFAC1.Text = Convert.ToString(currentTemplate.ZFAC1);
                txtZFAO1.Text = Convert.ToString(currentTemplate.ZFAO1);
                txtZFAC2.Text = Convert.ToString(currentTemplate.ZFAC2);
                txtZFAO2.Text = Convert.ToString(currentTemplate.ZFAO2);
                txtZFAC3.Text = Convert.ToString(currentTemplate.ZFAC3);
                txtZFAO3.Text = Convert.ToString(currentTemplate.ZFAO3);
            }
            if (type == TableTypeSteel.CoastalExposure || isAll)
            {
                txtCEBCExp1.Text = Convert.ToString(currentTemplate.CEBCExp1);
                txtCEBCExp2.Text = Convert.ToString(currentTemplate.CEBCExp2);
                txtCEBCExp3.Text = Convert.ToString(currentTemplate.CEBCExp3);
                txtCEBCExp4.Text = Convert.ToString(currentTemplate.CEBCExp4);
            }
            if (type == TableTypeSteel.SiteClassification || isAll)
            {
                txtSCSite1.Text = Convert.ToString(currentTemplate.SCSite1);
                txtSCSite2.Text = Convert.ToString(currentTemplate.SCSite2);
                txtSCSite3.Text = Convert.ToString(currentTemplate.SCSite3);
                txtSCSite4.Text = Convert.ToString(currentTemplate.SCSite4);
            }
        }

        #endregion

    }
}