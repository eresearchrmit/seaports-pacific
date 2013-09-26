using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.IO;

namespace CCIMT.Presentation.usercontrols
{
    public partial class materialpropertiesconcrete : BaseControl
    {

        #region Page Events

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                EnableConcreteEditMode(false);
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

        protected void btnCCREdit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnCCREdit.Text, Common.EVENT_NAME_EDIT))
            {
                SetConcreteEditMode(true, TableTypeConcrete.CarbonationCorrosionRatesAt20C);
                LoadDefaultConcreteTextValues(TableTypeConcrete.CarbonationCorrosionRatesAt20C, false);
                btnCCREdit.Text = Common.EVENT_NAME_SAVE;
                btnCCREdit.ValidationGroup = "CarbCR20";
                btnCCRCancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                    UpdateCCR();

                SetConcreteEditMode(false, TableTypeConcrete.CarbonationCorrosionRatesAt20C);
                ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
                LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.CarbonationCorrosionRatesAt20C, false);
                btnCCREdit.Text = Common.EVENT_NAME_EDIT;
                btnCCREdit.ValidationGroup = String.Empty;
                btnCCRCancel.Visible = false;                
            }
        }

        protected void btnCCRCancel_Click(object sender, EventArgs e)
        {
            SetConcreteEditMode(false, TableTypeConcrete.CarbonationCorrosionRatesAt20C);
            ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
            LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.CarbonationCorrosionRatesAt20C, false);
            btnCCREdit.Text = Common.EVENT_NAME_EDIT;
            btnCCREdit.ValidationGroup = String.Empty;
            btnCCRCancel.Visible = false;
        }

        protected void btnChCREdit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnChCREdit.Text, Common.EVENT_NAME_EDIT))
            {
                SetConcreteEditMode(true, TableTypeConcrete.ChlorideCorrosionRatesAt20C);
                LoadDefaultConcreteTextValues(TableTypeConcrete.ChlorideCorrosionRatesAt20C, false);
                btnChCREdit.Text = Common.EVENT_NAME_SAVE;
                btnChCREdit.ValidationGroup = "ChlCR20";
                btnChCRCancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                    UpdateChCR();

                SetConcreteEditMode(false, TableTypeConcrete.ChlorideCorrosionRatesAt20C);
                ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
                LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.ChlorideCorrosionRatesAt20C, false);
                btnChCREdit.Text = Common.EVENT_NAME_EDIT;
                btnChCREdit.ValidationGroup = String.Empty;
                btnChCRCancel.Visible = false;
            }
        }

        protected void btnChCRCancel_Click(object sender, EventArgs e)
        {
            SetConcreteEditMode(false, TableTypeConcrete.ChlorideCorrosionRatesAt20C);
            ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
            LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.ChlorideCorrosionRatesAt20C, false);
            btnChCREdit.Text = Common.EVENT_NAME_EDIT;
            btnChCREdit.ValidationGroup = String.Empty;
            btnChCRCancel.Visible = false;
        }

        protected void btnCO2DCEdit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnCO2DCEdit.Text, Common.EVENT_NAME_EDIT))
            {
                SetConcreteEditMode(true, TableTypeConcrete.CO2DiffusionCoefficient);
                LoadDefaultConcreteTextValues(TableTypeConcrete.CO2DiffusionCoefficient, false);
                btnCO2DCEdit.Text = Common.EVENT_NAME_SAVE;
                btnCO2DCEdit.ValidationGroup = "CO2DC";
                btnCO2DCCancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                    UpdateCO2DC();

                SetConcreteEditMode(false, TableTypeConcrete.CO2DiffusionCoefficient);
                ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
                LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.CO2DiffusionCoefficient, false);
                btnCO2DCEdit.Text = Common.EVENT_NAME_EDIT;
                btnCO2DCEdit.ValidationGroup = String.Empty;
                btnCO2DCCancel.Visible = false;
            }
        }

        protected void btnCO2DCCancel_Click(object sender, EventArgs e)
        {
            SetConcreteEditMode(false, TableTypeConcrete.CO2DiffusionCoefficient);
            ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
            LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.CO2DiffusionCoefficient, false);
            btnCO2DCEdit.Text = Common.EVENT_NAME_EDIT;
            btnCO2DCEdit.ValidationGroup = String.Empty;
            btnCO2DCCancel.Visible = false;
        }

        protected void btnAEEdit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnAEEdit.Text, Common.EVENT_NAME_EDIT))
            {
                SetConcreteEditMode(true, TableTypeConcrete.ActivationEnergy);
                LoadDefaultConcreteTextValues(TableTypeConcrete.ActivationEnergy, false);
                btnAEEdit.Text = Common.EVENT_NAME_SAVE;
                btnAEEdit.ValidationGroup = "AE";
                btnAECancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                    UpdateActivationEnergy();

                SetConcreteEditMode(false, TableTypeConcrete.ActivationEnergy);
                ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
                LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.ActivationEnergy, false);
                btnAEEdit.Text = Common.EVENT_NAME_EDIT;
                btnAEEdit.ValidationGroup = String.Empty;
                btnAECancel.Visible = false;
            }
        }

        protected void btnAECancel_Click(object sender, EventArgs e)
        {
            SetConcreteEditMode(false, TableTypeConcrete.ActivationEnergy);
            ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
            LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.ActivationEnergy, false);
            btnAEEdit.Text = Common.EVENT_NAME_EDIT;
            btnAEEdit.ValidationGroup = String.Empty;
            btnAECancel.Visible = false;
        }

        protected void btnEnvironmentEdit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnEnvironmentEdit.Text, Common.EVENT_NAME_EDIT))
            {
                SetConcreteEditMode(true, TableTypeConcrete.SurfaceChlorideConcentration);
                LoadDefaultConcreteTextValues(TableTypeConcrete.SurfaceChlorideConcentration, false);
                btnEnvironmentEdit.Text = Common.EVENT_NAME_SAVE;
                btnEnvironmentEdit.ValidationGroup = "SCC";
                btnEnvironmentCancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                    UpdateEnvironment();

                SetConcreteEditMode(false, TableTypeConcrete.SurfaceChlorideConcentration);
                ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
                LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.SurfaceChlorideConcentration, false);
                btnEnvironmentEdit.Text = Common.EVENT_NAME_EDIT;
                btnEnvironmentEdit.ValidationGroup = String.Empty;
                btnEnvironmentCancel.Visible = false;
            }
        }

        protected void btnEnvironmentCancel_Click(object sender, EventArgs e)
        {
            SetConcreteEditMode(false, TableTypeConcrete.SurfaceChlorideConcentration);
            ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
            LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.SurfaceChlorideConcentration, false);
            btnEnvironmentEdit.Text = Common.EVENT_NAME_EDIT;
            btnEnvironmentEdit.ValidationGroup = String.Empty;
            btnEnvironmentCancel.Visible = false;
        }

        protected void btnCDCEdit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnCDCEdit.Text, Common.EVENT_NAME_EDIT))
            {
                SetConcreteEditMode(true, TableTypeConcrete.ChlorideDiffusionCoefficient);
                LoadDefaultConcreteTextValues(TableTypeConcrete.ChlorideDiffusionCoefficient, false);
                btnCDCEdit.Text = Common.EVENT_NAME_SAVE;
                btnCDCEdit.ValidationGroup = "CDC";
                btnCDCCancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                    UpdateCDC();

                SetConcreteEditMode(false, TableTypeConcrete.ChlorideDiffusionCoefficient);
                ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
                LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.ChlorideDiffusionCoefficient, false);
                btnCDCEdit.Text = Common.EVENT_NAME_EDIT;
                btnCDCEdit.ValidationGroup = String.Empty;
                btnCDCCancel.Visible = false;
            }
        }

        protected void btnCDCCancel_Click(object sender, EventArgs e)
        {
            SetConcreteEditMode(false, TableTypeConcrete.ChlorideDiffusionCoefficient);
            ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
            LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.ChlorideDiffusionCoefficient, false);
            btnCDCEdit.Text = Common.EVENT_NAME_EDIT;
            btnCDCEdit.ValidationGroup = String.Empty;
            btnCDCCancel.Visible = false;
        }

        protected void btnEnvFactEdit_Click(object sender, EventArgs e)
        {
            //if (String.Equals(btnEnvFactEdit.Text, Common.EVENT_NAME_EDIT))
            //{
            //    btnEnvFactEdit.Text = Common.EVENT_NAME_SAVE;
            //    btnEnvFactCancel.Visible = true;
            //}
            //else
            //{
            //    if (CurrentSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
            //        UpdateEnvFact();

            //    btnEnvFactEdit.Text = Common.EVENT_NAME_EDIT;
            //    btnEnvFactCancel.Visible = false;
            //}
        }

        protected void btnEnvFactCancel_Click(object sender, EventArgs e)
        {
            //btnEnvFactEdit.Text = Common.EVENT_NAME_EDIT;
            //btnEnvFactCancel.Visible = false;
        }

        protected void btnConcreteLegendEdit_Click(object sender, EventArgs e)
        {
            if (String.Equals(btnConcreteLegendEdit.Text, Common.EVENT_NAME_EDIT))
            {
                SetConcreteEditMode(true, TableTypeConcrete.Legend);
                LoadDefaultConcreteTextValues(TableTypeConcrete.Legend, false);
                btnConcreteLegendEdit.Text = Common.EVENT_NAME_SAVE;
                btnConcreteLegendEdit.ValidationGroup ="LGND";
                btnConcreteLegendCancel.Visible = true;
            }
            else
            {
                if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                    UpdateConcreteLegends();

                SetConcreteEditMode(false, TableTypeConcrete.Legend);
                ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
                LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.Legend, false);
                btnConcreteLegendEdit.Text = Common.EVENT_NAME_EDIT;
                btnConcreteLegendEdit.ValidationGroup = String.Empty;
                btnConcreteLegendCancel.Visible = false;
            }
        }

        protected void btnConcreteLegendCancel_Click(object sender, EventArgs e)
        {
            SetConcreteEditMode(false, TableTypeConcrete.Legend);
            ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
            LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.Legend, false);
            btnConcreteLegendEdit.Text = Common.EVENT_NAME_EDIT;
            btnConcreteLegendEdit.ValidationGroup = String.Empty;
            btnConcreteLegendCancel.Visible = false;
        }

        #endregion
               
        #region Private Methods

        #region Show/Hide Controls

        private void SetConcreteEditMode(bool isEditMode, TableTypeConcrete type)
        {
            if (isEditMode)
            {
                ShowConcreteTextBoxes(type);
                HideConcreteLabels(type);
            }
            else
            {
                HideConcreteTextBoxes(type);
                ShowConcreteLabels(type);
            }
        }

        private void ShowConcreteTextBoxes(TableTypeConcrete type)
        {
            switch (type)
            {
                case TableTypeConcrete.CarbonationCorrosionRatesAt20C:
                    txtCB1Mean.Visible = true;
                    txtCB1SD.Visible = true;
                    txtCB2Mean.Visible = true;
                    txtCB2SD.Visible = true;
                    txtCB3Mean.Visible = true;
                    txtCB3SD.Visible = true;
                    txtCB4Mean.Visible = true;
                    txtCB4SD.Visible = true;
                    break;
                case TableTypeConcrete.ChlorideCorrosionRatesAt20C:
                    txtCL1Mean.Visible = true;
                    txtCL1SD.Visible = true;
                    txtCL2Mean.Visible = true;
                    txtCL2SD.Visible = true;
                    txtCL3Mean.Visible = true;
                    txtCL3SD.Visible = true;
                    txtCL4Mean.Visible = true;
                    txtCL4SD.Visible = true;
                    txtCL5Mean.Visible = true;
                    txtCL5SD.Visible = true;
                    break;
                case TableTypeConcrete.CO2DiffusionCoefficient:
                    txtCO2DC30D1.Visible = true;
                    txtCO2DC30Nd.Visible = true;
                    txtCO2DC35D1.Visible = true;
                    txtCO2DC35Nd.Visible = true;
                    txtCO2DC40D1.Visible = true;
                    txtCO2DC40Nd.Visible = true;
                    txtCO2DC45D1.Visible = true;
                    txtCO2DC45Nd.Visible = true;
                    txtCO2DC50D1.Visible = true;
                    txtCO2DC50Nd.Visible = true;
                    txtCO2DC55D1.Visible = true;
                    txtCO2DC55Nd.Visible = true;
                    txtCO2DC60D1.Visible = true;
                    txtCO2DC60Nd.Visible = true;
                    txtCO2DC65D1.Visible = true;
                    txtCO2DC65Nd.Visible = true;
                    break;
                case TableTypeConcrete.ActivationEnergy:
                    txtAE30Energy.Visible = true;
                    txtAE40Energy.Visible = true;
                    txtAE50Energy.Visible = true;
                    txtAE60Energy.Visible = true;
                    txtAE70Energy.Visible = true;
                    break;
                case TableTypeConcrete.SurfaceChlorideConcentration:
                    txtEnv1TidSplMean.Visible = true;
                    txtEnv2AtmNearMean.Visible = true;
                    txtEnv3AtmFarMean.Visible = true;
                    break;
                case TableTypeConcrete.ChlorideDiffusionCoefficient:
                    txtCDC40Dc.Visible = true;
                    txtCDC40Fc.Visible = true;
                    txtCDC45Dc.Visible = true;
                    txtCDC45Fc.Visible = true;
                    txtCDC50Dc.Visible = true;
                    txtCDC50Fc.Visible = true;
                    txtCDC55Dc.Visible = true;
                    txtCDC55Fc.Visible = true;
                    break;
                case TableTypeConcrete.Legend:
                    txtLegKurban.Visible = true;
                    txtLegNM.Visible = true;
                    break;
            }
        }

        private void ShowConcreteLabels(TableTypeConcrete type)
        {
            switch (type)
            {
                case TableTypeConcrete.CarbonationCorrosionRatesAt20C:
                    lblCB1Mean.Visible = true;
                    lblCB1SD.Visible = true;
                    lblCB2Mean.Visible = true;
                    lblCB2SD.Visible = true;
                    lblCB3Mean.Visible = true;
                    lblCB3SD.Visible = true;
                    lblCB4Mean.Visible = true;
                    lblCB4SD.Visible = true;
                    break;
                case TableTypeConcrete.ChlorideCorrosionRatesAt20C:
                    lblCL1Mean.Visible = true;
                    lblCL1SD.Visible = true;
                    lblCL2Mean.Visible = true;
                    lblCL2SD.Visible = true;
                    lblCL3Mean.Visible = true;
                    lblCL3SD.Visible = true;
                    lblCL4Mean.Visible = true;
                    lblCL4SD.Visible = true;
                    lblCL5Mean.Visible = true;
                    lblCL5SD.Visible = true;
                    break;
                case TableTypeConcrete.CO2DiffusionCoefficient:
                    lblCO2DC30D1.Visible = true;
                    lblCO2DC30Nd.Visible = true;
                    lblCO2DC35D1.Visible = true;
                    lblCO2DC35Nd.Visible = true;
                    lblCO2DC40D1.Visible = true;
                    lblCO2DC40Nd.Visible = true;
                    lblCO2DC45D1.Visible = true;
                    lblCO2DC45Nd.Visible = true;
                    lblCO2DC50D1.Visible = true;
                    lblCO2DC50Nd.Visible = true;
                    lblCO2DC55D1.Visible = true;
                    lblCO2DC55Nd.Visible = true;
                    lblCO2DC60D1.Visible = true;
                    lblCO2DC60Nd.Visible = true;
                    lblCO2DC65D1.Visible = true;
                    lblCO2DC65Nd.Visible = true;
                    break;
                case TableTypeConcrete.ActivationEnergy:
                    lblAE30Energy.Visible = true;
                    lblAE40Energy.Visible = true;
                    lblAE50Energy.Visible = true;
                    lblAE60Energy.Visible = true;
                    lblAE70Energy.Visible = true;
                    break;
                case TableTypeConcrete.SurfaceChlorideConcentration:
                    lblEnv1TidSplMean.Visible = true;
                    lblEnv2AtmNearMean.Visible = true;
                    lblEnv3AtmFarMean.Visible = true;
                    break;
                case TableTypeConcrete.ChlorideDiffusionCoefficient:
                    lblCDC40Dc.Visible = true;
                    lblCDC40Fc.Visible = true;
                    lblCDC45Dc.Visible = true;
                    lblCDC45Fc.Visible = true;
                    lblCDC50Dc.Visible = true;
                    lblCDC50Fc.Visible = true;
                    lblCDC55Dc.Visible = true;
                    lblCDC55Fc.Visible = true;
                    break;
                case TableTypeConcrete.Legend:
                    lblLegKurban.Visible = true;
                    lblLegNM.Visible = true;
                    break;
            }
        }

        private void HideConcreteTextBoxes(TableTypeConcrete type)
        {
            switch (type)
            {
                case TableTypeConcrete.CarbonationCorrosionRatesAt20C:
                    txtCB1Mean.Visible = false;
                    txtCB1SD.Visible = false;
                    txtCB2Mean.Visible = false;
                    txtCB2SD.Visible = false;
                    txtCB3Mean.Visible = false;
                    txtCB3SD.Visible = false;
                    txtCB4Mean.Visible = false;
                    txtCB4SD.Visible = false;
                    break;
                case TableTypeConcrete.ChlorideCorrosionRatesAt20C:
                    txtCL1Mean.Visible = false;
                    txtCL1SD.Visible = false;
                    txtCL2Mean.Visible = false;
                    txtCL2SD.Visible = false;
                    txtCL3Mean.Visible = false;
                    txtCL3SD.Visible = false;
                    txtCL4Mean.Visible = false;
                    txtCL4SD.Visible = false;
                    txtCL5Mean.Visible = false;
                    txtCL5SD.Visible = false;
                    break;
                case TableTypeConcrete.CO2DiffusionCoefficient:
                    txtCO2DC30D1.Visible = false;
                    txtCO2DC30Nd.Visible = false;
                    txtCO2DC35D1.Visible = false;
                    txtCO2DC35Nd.Visible = false;
                    txtCO2DC40D1.Visible = false;
                    txtCO2DC40Nd.Visible = false;
                    txtCO2DC45D1.Visible = false;
                    txtCO2DC45Nd.Visible = false;
                    txtCO2DC50D1.Visible = false;
                    txtCO2DC50Nd.Visible = false;
                    txtCO2DC55D1.Visible = false;
                    txtCO2DC55Nd.Visible = false;
                    txtCO2DC60D1.Visible = false;
                    txtCO2DC60Nd.Visible = false;
                    txtCO2DC65D1.Visible = false;
                    txtCO2DC65Nd.Visible = false;
                    break;
                case TableTypeConcrete.ActivationEnergy:
                    txtAE30Energy.Visible = false;
                    txtAE40Energy.Visible = false;
                    txtAE50Energy.Visible = false;
                    txtAE60Energy.Visible = false;
                    txtAE70Energy.Visible = false;
                    break;
                case TableTypeConcrete.SurfaceChlorideConcentration:
                    txtEnv1TidSplMean.Visible = false;
                    txtEnv2AtmNearMean.Visible = false;
                    txtEnv3AtmFarMean.Visible = false;
                    break;
                case TableTypeConcrete.ChlorideDiffusionCoefficient:
                    txtCDC40Dc.Visible = false;
                    txtCDC40Fc.Visible = false;
                    txtCDC45Dc.Visible = false;
                    txtCDC45Fc.Visible = false;
                    txtCDC50Dc.Visible = false;
                    txtCDC50Fc.Visible = false;
                    txtCDC55Dc.Visible = false;
                    txtCDC55Fc.Visible = false;
                    break;
                case TableTypeConcrete.Legend:
                    txtLegKurban.Visible = false;
                    txtLegNM.Visible = false;
                    break;
            }
        }

        private void HideConcreteLabels(TableTypeConcrete type)
        {
            switch (type)
            {
                case TableTypeConcrete.CarbonationCorrosionRatesAt20C:
                    lblCB1Mean.Visible = false;
                    lblCB1SD.Visible = false;
                    lblCB2Mean.Visible = false;
                    lblCB2SD.Visible = false;
                    lblCB3Mean.Visible = false;
                    lblCB3SD.Visible = false;
                    lblCB4Mean.Visible = false;
                    lblCB4SD.Visible = false;
                    break;
                case TableTypeConcrete.ChlorideCorrosionRatesAt20C:
                    lblCL1Mean.Visible = false;
                    lblCL1SD.Visible = false;
                    lblCL2Mean.Visible = false;
                    lblCL2SD.Visible = false;
                    lblCL3Mean.Visible = false;
                    lblCL3SD.Visible = false;
                    lblCL4Mean.Visible = false;
                    lblCL4SD.Visible = false;
                    lblCL5Mean.Visible = false;
                    lblCL5SD.Visible = false; break;
                case TableTypeConcrete.CO2DiffusionCoefficient:
                    lblCO2DC30D1.Visible = false;
                    lblCO2DC30Nd.Visible = false;
                    lblCO2DC35D1.Visible = false;
                    lblCO2DC35Nd.Visible = false;
                    lblCO2DC40D1.Visible = false;
                    lblCO2DC40Nd.Visible = false;
                    lblCO2DC45D1.Visible = false;
                    lblCO2DC45Nd.Visible = false;
                    lblCO2DC50D1.Visible = false;
                    lblCO2DC50Nd.Visible = false;
                    lblCO2DC55D1.Visible = false;
                    lblCO2DC55Nd.Visible = false;
                    lblCO2DC60D1.Visible = false;
                    lblCO2DC60Nd.Visible = false;
                    lblCO2DC65D1.Visible = false;
                    lblCO2DC65Nd.Visible = false;
                    break;
                case TableTypeConcrete.ActivationEnergy:
                    lblAE30Energy.Visible = false;
                    lblAE40Energy.Visible = false;
                    lblAE50Energy.Visible = false;
                    lblAE60Energy.Visible = false;
                    lblAE70Energy.Visible = false;
                    break;
                case TableTypeConcrete.SurfaceChlorideConcentration:
                    lblEnv1TidSplMean.Visible = false;
                    lblEnv2AtmNearMean.Visible = false;
                    lblEnv3AtmFarMean.Visible = false;
                    break;
                case TableTypeConcrete.ChlorideDiffusionCoefficient:
                    lblCDC40Dc.Visible = false;
                    lblCDC40Fc.Visible = false;
                    lblCDC45Dc.Visible = false;
                    lblCDC45Fc.Visible = false;
                    lblCDC50Dc.Visible = false;
                    lblCDC50Fc.Visible = false;
                    lblCDC55Dc.Visible = false;
                    lblCDC55Fc.Visible = false;
                    break;
                case TableTypeConcrete.Legend:
                    lblLegKurban.Visible = false;
                    lblLegNM.Visible = false; ;
                    break;
            }
        }

        #endregion

        #region Update Methods

        private void UpdateCCR()
        {
            CurrentConcreteTemplate.isCarbCR20Updated = true;
            CurrentConcreteTemplate.CB1Mean = Convert.ToDecimal(txtCB1Mean.Text);
            CurrentConcreteTemplate.CB1SD = Convert.ToDecimal(txtCB1SD.Text);
            CurrentConcreteTemplate.CB2Mean = Convert.ToDecimal(txtCB2Mean.Text);
            CurrentConcreteTemplate.CB2SD = Convert.ToDecimal(txtCB2SD.Text);
            CurrentConcreteTemplate.CB3Mean = Convert.ToDecimal(txtCB3Mean.Text);
            CurrentConcreteTemplate.CB3SD = Convert.ToDecimal(txtCB3SD.Text);
            CurrentConcreteTemplate.CB4Mean = Convert.ToDecimal(txtCB4Mean.Text);
            CurrentConcreteTemplate.CB4SD = Convert.ToDecimal(txtCB4SD.Text);
        }

        private void UpdateChCR()
        {
            CurrentConcreteTemplate.isChlCR20Updated = true;
            CurrentConcreteTemplate.CL1Mean = Convert.ToDecimal(txtCL1Mean.Text);
            CurrentConcreteTemplate.CL1SD = Convert.ToDecimal(txtCL1SD.Text);
            CurrentConcreteTemplate.CL2Mean = Convert.ToDecimal(txtCL2Mean.Text);
            CurrentConcreteTemplate.CL2SD = Convert.ToDecimal(txtCL2SD.Text);
            CurrentConcreteTemplate.CL3Mean = Convert.ToDecimal(txtCL3Mean.Text);
            CurrentConcreteTemplate.CL3SD = Convert.ToDecimal(txtCL3SD.Text);
            CurrentConcreteTemplate.CL4Mean = Convert.ToDecimal(txtCL4Mean.Text);
            CurrentConcreteTemplate.CL4SD = Convert.ToDecimal(txtCL4SD.Text);
            CurrentConcreteTemplate.CL5Mean = Convert.ToDecimal(txtCL5Mean.Text);
            CurrentConcreteTemplate.CL5SD = Convert.ToDecimal(txtCL5SD.Text);
        }

        private void UpdateCO2DC()
        {
            CurrentConcreteTemplate.isCO2DCUpdated = true;
            CurrentConcreteTemplate.coVal30D1 = Convert.ToDecimal(txtCO2DC30D1.Text);
            CurrentConcreteTemplate.coVal30ND = Convert.ToDecimal(txtCO2DC30Nd.Text);
            CurrentConcreteTemplate.coVal35D1 = Convert.ToDecimal(txtCO2DC35D1.Text);
            CurrentConcreteTemplate.coVal35ND = Convert.ToDecimal(txtCO2DC35Nd.Text);
            CurrentConcreteTemplate.coVal40D1 = Convert.ToDecimal(txtCO2DC40D1.Text);
            CurrentConcreteTemplate.coVal40ND = Convert.ToDecimal(txtCO2DC40Nd.Text);
            CurrentConcreteTemplate.coVal45D1 = Convert.ToDecimal(txtCO2DC45D1.Text);
            CurrentConcreteTemplate.coVal45ND = Convert.ToDecimal(txtCO2DC45Nd.Text);
            CurrentConcreteTemplate.coVal50D1 = Convert.ToDecimal(txtCO2DC50D1.Text);
            CurrentConcreteTemplate.coVal50ND = Convert.ToDecimal(txtCO2DC50Nd.Text);
            CurrentConcreteTemplate.coVal55D1 = Convert.ToDecimal(txtCO2DC55D1.Text);
            CurrentConcreteTemplate.coVal55ND = Convert.ToDecimal(txtCO2DC55Nd.Text);
            CurrentConcreteTemplate.coVal60D1 = Convert.ToDecimal(txtCO2DC60D1.Text);
            CurrentConcreteTemplate.coVal60ND = Convert.ToDecimal(txtCO2DC60Nd.Text);
            CurrentConcreteTemplate.coVal65D1 = Convert.ToDecimal(txtCO2DC65D1.Text);
            CurrentConcreteTemplate.coVal65ND = Convert.ToDecimal(txtCO2DC65Nd.Text);
        }

        private void UpdateActivationEnergy()
        {
            CurrentConcreteTemplate.isAEUpdated = true;
            CurrentConcreteTemplate.aeVal3E = Convert.ToDecimal(txtAE30Energy.Text);
            CurrentConcreteTemplate.aeVal4E = Convert.ToDecimal(txtAE40Energy.Text);
            CurrentConcreteTemplate.aeVal5E = Convert.ToDecimal(txtAE50Energy.Text);
            CurrentConcreteTemplate.aeVal6E = Convert.ToDecimal(txtAE60Energy.Text);
            CurrentConcreteTemplate.aeVal7E = Convert.ToDecimal(txtAE70Energy.Text);
        }

        private void UpdateEnvironment()
        {
            CurrentConcreteTemplate.isSCCUpdated = true;
            CurrentConcreteTemplate.sccTidalSplashMean = Convert.ToDecimal(txtEnv1TidSplMean.Text);
            //decimal env1Type = Convert.ToDecimal(txtEnv1Type.Text);
            CurrentConcreteTemplate.sccAtmNearCoastMean = Convert.ToDecimal(txtEnv2AtmNearMean.Text);
            //decimal env2Type = Convert.ToDecimal(txtEnv2Type.Text);
            CurrentConcreteTemplate.sccAtmFarCoastMean = Convert.ToDecimal(txtEnv3AtmFarMean.Text);
            //decimal env3Type = Convert.ToDecimal(txtEnv3Type.Text);
        }

        private void UpdateCDC()
        {
            CurrentConcreteTemplate.isCDCUpdated = true;
            CurrentConcreteTemplate.cdcVal40FC = Convert.ToDecimal(txtCDC40Fc.Text);
            CurrentConcreteTemplate.cdcVal40DC = Convert.ToDecimal(txtCDC40Dc.Text);
            CurrentConcreteTemplate.cdcVal45FC = Convert.ToDecimal(txtCDC45Fc.Text);
            CurrentConcreteTemplate.cdcVal45DC = Convert.ToDecimal(txtCDC45Dc.Text);
            CurrentConcreteTemplate.cdcVal50FC = Convert.ToDecimal(txtCDC50Fc.Text);
            CurrentConcreteTemplate.cdcVal50DC = Convert.ToDecimal(txtCDC50Dc.Text);
            CurrentConcreteTemplate.cdcVal55FC = Convert.ToDecimal(txtCDC55Fc.Text);
            CurrentConcreteTemplate.cdcVal55DC = Convert.ToDecimal(txtCDC55Dc.Text);
        }

        private void UpdateEnvFact()
        {
            //decimal envFact1NMean = Convert.ToDecimal(txtEnvFact1NMean.Text);
            //decimal envFact1NSD = Convert.ToDecimal(txtEnvFact1NSD.Text);
            //decimal envFact1KMean = Convert.ToDecimal(txtEnvFact1KMean.Text);
            //decimal envFact1KSD = Convert.ToDecimal(txtEnvFact1KSD.Text);
            //decimal envFact2NMean = Convert.ToDecimal(txtEnvFact2NMean.Text);
            //decimal envFact2NSD = Convert.ToDecimal(txtEnvFact2NSD.Text);
            //decimal envFact2KMean = Convert.ToDecimal(txtEnvFact2KMean.Text);
            //decimal envFact2KSD = Convert.ToDecimal(txtEnvFact2KSD.Text);
            //decimal envFact3NMean = Convert.ToDecimal(txtEnvFact3NMean.Text);
            //decimal envFact3NSD = Convert.ToDecimal(txtEnvFact3NSD.Text);
            //decimal envFact3KMean = Convert.ToDecimal(txtEnvFact3KMean.Text);
            //decimal envFact3KSD = Convert.ToDecimal(txtEnvFact3KSD.Text);
            //decimal envFact4NMean = Convert.ToDecimal(txtEnvFact4NMean.Text);
            //decimal envFact4NSD = Convert.ToDecimal(txtEnvFact4NSD.Text);
            //decimal envFact4KMean = Convert.ToDecimal(txtEnvFact4KMean.Text);
            //decimal envFact4KSD = Convert.ToDecimal(txtEnvFact4KSD.Text);
        }

        private void UpdateConcreteLegends()
        {
            CurrentConcreteTemplate.isLegendsUpdated = true;
            CurrentConcreteTemplate.legendKUrban = Convert.ToDecimal(txtLegKurban.Text);
            CurrentConcreteTemplate.legendNM = Convert.ToDecimal(txtLegNM.Text);
        }

        #endregion

        private void EnableConcreteEditMode(bool isEditMode)
        {
            SetConcreteEditMode(isEditMode, TableTypeConcrete.CarbonationCorrosionRatesAt20C);
            SetConcreteEditMode(isEditMode, TableTypeConcrete.ChlorideCorrosionRatesAt20C);
            SetConcreteEditMode(isEditMode, TableTypeConcrete.CO2DiffusionCoefficient);
            SetConcreteEditMode(isEditMode, TableTypeConcrete.ActivationEnergy);
            SetConcreteEditMode(isEditMode, TableTypeConcrete.SurfaceChlorideConcentration);
            SetConcreteEditMode(isEditMode, TableTypeConcrete.ChlorideDiffusionCoefficient);
            SetConcreteEditMode(isEditMode, TableTypeConcrete.Legend);
        }

        #endregion

        #region Public Methods

        public void LoadDefaultConcreteLabelValues(ConcreteTemplate currentTemplate, TableTypeConcrete type, bool isAll)
        {
            if (type == TableTypeConcrete.CarbonationCorrosionRatesAt20C || isAll)
            {
                lblCB1Mean.Text = Convert.ToString(currentTemplate.CB1Mean);
                lblCB1SD.Text = Convert.ToString(currentTemplate.CB1SD);
                lblCB2Mean.Text = Convert.ToString(currentTemplate.CB2Mean);
                lblCB2SD.Text = Convert.ToString(currentTemplate.CB2SD);
                lblCB3Mean.Text = Convert.ToString(currentTemplate.CB3Mean);
                lblCB3SD.Text = Convert.ToString(currentTemplate.CB3SD);
                lblCB4Mean.Text = Convert.ToString(currentTemplate.CB4Mean);
                lblCB4SD.Text = Convert.ToString(currentTemplate.CB4SD);
            }
            if (type == TableTypeConcrete.ChlorideCorrosionRatesAt20C || isAll)
            {
                lblCL1Mean.Text = Convert.ToString(currentTemplate.CL1Mean);
                lblCL1SD.Text = Convert.ToString(currentTemplate.CL1SD);
                lblCL2Mean.Text = Convert.ToString(currentTemplate.CL2Mean);
                lblCL2SD.Text = Convert.ToString(currentTemplate.CL2SD);
                lblCL3Mean.Text = Convert.ToString(currentTemplate.CL3Mean);
                lblCL3SD.Text = Convert.ToString(currentTemplate.CL3SD);
                lblCL4Mean.Text = Convert.ToString(currentTemplate.CL4Mean);
                lblCL4SD.Text = Convert.ToString(currentTemplate.CL4SD);
                lblCL5Mean.Text = Convert.ToString(currentTemplate.CL5Mean);
                lblCL5SD.Text = Convert.ToString(currentTemplate.CL5SD);
            }
            if (type == TableTypeConcrete.CO2DiffusionCoefficient || isAll)
            {
                lblCO2DC30D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal30D1, 4));
                lblCO2DC30Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal30ND, 4));
                lblCO2DC35D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal35D1, 4));
                lblCO2DC35Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal35ND, 4));
                lblCO2DC40D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal40D1, 4));
                lblCO2DC40Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal40ND, 4));
                lblCO2DC45D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal45D1, 4));
                lblCO2DC45Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal45ND, 4));
                lblCO2DC50D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal50D1, 4));
                lblCO2DC50Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal50ND, 4));
                lblCO2DC55D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal55D1, 4));
                lblCO2DC55Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal55ND, 4));
                lblCO2DC60D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal60D1, 4));
                lblCO2DC60Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal60ND, 4));
                lblCO2DC65D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal65D1, 4));
                lblCO2DC65Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal65ND, 4));
            }
            if (type == TableTypeConcrete.ActivationEnergy || isAll)
            {
                lblAE30Energy.Text = Convert.ToString(currentTemplate.aeVal3E);
                lblAE40Energy.Text = Convert.ToString(currentTemplate.aeVal4E);
                lblAE50Energy.Text = Convert.ToString(currentTemplate.aeVal5E);
                lblAE60Energy.Text = Convert.ToString(currentTemplate.aeVal6E);
                lblAE70Energy.Text = Convert.ToString(currentTemplate.aeVal7E);
            }
            if (type == TableTypeConcrete.SurfaceChlorideConcentration || isAll)
            {
                lblEnv1TidSplMean.Text = Convert.ToString(currentTemplate.sccTidalSplashMean);
                lblEnv2AtmNearMean.Text = Convert.ToString(currentTemplate.sccAtmNearCoastMean);
                lblEnv3AtmFarMean.Text = Convert.ToString(currentTemplate.sccAtmFarCoastMean);
            }
            if (type == TableTypeConcrete.ChlorideDiffusionCoefficient || isAll)
            {
                lblCDC40Dc.Text = Convert.ToString(currentTemplate.cdcVal40DC);
                lblCDC40Fc.Text = Convert.ToString(currentTemplate.cdcVal40FC);
                lblCDC45Dc.Text = Convert.ToString(currentTemplate.cdcVal45DC);
                lblCDC45Fc.Text = Convert.ToString(currentTemplate.cdcVal45FC);
                lblCDC50Dc.Text = Convert.ToString(currentTemplate.cdcVal50DC);
                lblCDC50Fc.Text = Convert.ToString(currentTemplate.cdcVal50FC);
                lblCDC55Dc.Text = Convert.ToString(currentTemplate.cdcVal55DC);
                lblCDC55Fc.Text = Convert.ToString(currentTemplate.cdcVal55FC);
            }
            if (type == TableTypeConcrete.Legend || isAll)
            {
                lblLegKurban.Text = Convert.ToString(currentTemplate.legendKUrban);
                lblLegNM.Text = Convert.ToString(currentTemplate.legendNM);
            }

            UserSelection.ConcreteTemplate = currentTemplate;
        }

        public void LoadDefaultConcreteTextValues(TableTypeConcrete type, bool isAll)   
        {
            ConcreteTemplate currentTemplate = CurrentConcreteTemplate;

            if (type == TableTypeConcrete.CarbonationCorrosionRatesAt20C || isAll)
            {
                txtCB1Mean.Text = Convert.ToString(currentTemplate.CB1Mean);
                txtCB1SD.Text = Convert.ToString(currentTemplate.CB1SD);
                txtCB2Mean.Text = Convert.ToString(currentTemplate.CB2Mean);
                txtCB2SD.Text = Convert.ToString(currentTemplate.CB2SD);
                txtCB3Mean.Text = Convert.ToString(currentTemplate.CB3Mean);
                txtCB3SD.Text = Convert.ToString(currentTemplate.CB3SD);
                txtCB4Mean.Text = Convert.ToString(currentTemplate.CB4Mean);
                txtCB4SD.Text = Convert.ToString(currentTemplate.CB4SD);
            }
            if (type == TableTypeConcrete.ChlorideCorrosionRatesAt20C || isAll)
            {
                txtCL1Mean.Text = Convert.ToString(currentTemplate.CL1Mean);
                txtCL1SD.Text = Convert.ToString(currentTemplate.CL1SD);
                txtCL2Mean.Text = Convert.ToString(currentTemplate.CL2Mean);
                txtCL2SD.Text = Convert.ToString(currentTemplate.CL2SD);
                txtCL3Mean.Text = Convert.ToString(currentTemplate.CL3Mean);
                txtCL3SD.Text = Convert.ToString(currentTemplate.CL3SD);
                txtCL4Mean.Text = Convert.ToString(currentTemplate.CL4Mean);
                txtCL4SD.Text = Convert.ToString(currentTemplate.CL4SD);
                txtCL5Mean.Text = Convert.ToString(currentTemplate.CL5Mean);
                txtCL5SD.Text = Convert.ToString(currentTemplate.CL5SD);
            }
            if (type == TableTypeConcrete.CO2DiffusionCoefficient || isAll)
            {
                txtCO2DC30D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal30D1, 4));
                txtCO2DC30Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal30ND, 4));
                txtCO2DC35D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal35D1, 4));
                txtCO2DC35Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal35ND, 4));
                txtCO2DC40D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal40D1, 4));
                txtCO2DC40Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal40ND, 4));
                txtCO2DC45D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal45D1, 4));
                txtCO2DC45Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal45ND, 4));
                txtCO2DC50D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal50D1, 4));
                txtCO2DC50Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal50ND, 4));
                txtCO2DC55D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal55D1, 4));
                txtCO2DC55Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal55ND, 4));
                txtCO2DC60D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal60D1, 4));
                txtCO2DC60Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal60ND, 4));
                txtCO2DC65D1.Text = Convert.ToString(decimal.Round(currentTemplate.coVal65D1, 4));
                txtCO2DC65Nd.Text = Convert.ToString(decimal.Round(currentTemplate.coVal65ND, 4));
            }
            if (type == TableTypeConcrete.ActivationEnergy || isAll)
            {
                txtAE30Energy.Text = Convert.ToString(currentTemplate.aeVal3E);
                txtAE40Energy.Text = Convert.ToString(currentTemplate.aeVal4E);
                txtAE50Energy.Text = Convert.ToString(currentTemplate.aeVal5E);
                txtAE60Energy.Text = Convert.ToString(currentTemplate.aeVal6E);
                txtAE70Energy.Text = Convert.ToString(currentTemplate.aeVal7E);
            }
            if (type == TableTypeConcrete.SurfaceChlorideConcentration || isAll)
            {
                txtEnv1TidSplMean.Text = Convert.ToString(currentTemplate.sccTidalSplashMean);
                txtEnv2AtmNearMean.Text = Convert.ToString(currentTemplate.sccAtmNearCoastMean);
                txtEnv3AtmFarMean.Text = Convert.ToString(currentTemplate.sccAtmFarCoastMean);
            }
            if (type == TableTypeConcrete.ChlorideDiffusionCoefficient || isAll)
            {
                txtCDC40Dc.Text = Convert.ToString(currentTemplate.cdcVal40DC);
                txtCDC40Fc.Text = Convert.ToString(currentTemplate.cdcVal40FC);
                txtCDC45Dc.Text = Convert.ToString(currentTemplate.cdcVal45DC);
                txtCDC45Fc.Text = Convert.ToString(currentTemplate.cdcVal45FC);
                txtCDC50Dc.Text = Convert.ToString(currentTemplate.cdcVal50DC);
                txtCDC50Fc.Text = Convert.ToString(currentTemplate.cdcVal50FC);
                txtCDC55Dc.Text = Convert.ToString(currentTemplate.cdcVal55DC);
                txtCDC55Fc.Text = Convert.ToString(currentTemplate.cdcVal55FC);
            }
            if (type == TableTypeConcrete.Legend || isAll)
            {
                txtLegKurban.Text = Convert.ToString(currentTemplate.legendKUrban);
                txtLegNM.Text = Convert.ToString(currentTemplate.legendNM);
            }
        }

        #endregion

    }
}