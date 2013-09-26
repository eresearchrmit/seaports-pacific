using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

using System.Data.OleDb;
using System.Data;
using Excel = Microsoft.Office.Interop.Excel;
using CCIMT.Presentation.usercontrols;

namespace CCIMT.Presentation
{
    public partial class materialproperties : BasePage
    {

        #region Page Events

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                LoadCurrentSelection();
            }
        }

        protected void btnViewEdit_Click(object sender, EventArgs e)
        {
            int materialTypeId = Convert.ToInt16(drpMaterial.SelectedItem.Value);
            ValidateMaterialSelection(materialTypeId);
        }
        
        #endregion

        #region Private Methods

        private void ValidateMaterialSelection(int materialTypeId)
        {
            CurrentSelection selection = UserSelection;
            //base.CurrentConcreteTemplate = null;
            //base.CurrentSteelTemplate = null;
            //base.CurrentTimberTemplate = null;

            if (materialTypeId == 0)
            {
                //lblMaterial.Visible = true;
                divViewConcrete.Visible = false;
                divViewSteel.Visible = false;
                divViewTimber.Visible = false;
            }
            else if (materialTypeId == 1)
            {
                selection.SelectedTemplateType = TemplateType.ConcreteTempate;
                
                materialpropertiesconcrete controlConcrete = ((materialproperties)Page).UCMaterialPropertiesConcrete1;
                ConcreteTemplate currentTemplate = CurrentConcreteTemplate;
                controlConcrete.LoadDefaultConcreteLabelValues(currentTemplate, TableTypeConcrete.None, true);
                
                //lblMaterial.Visible = false;
                divViewConcrete.Visible = true;
                divViewSteel.Visible = false;
                divViewTimber.Visible = false;
            }
            else if (materialTypeId == 2)
            {
                selection.SelectedTemplateType = TemplateType.SteelTemplate;

                materialpropertiessteel controlSteel = ((materialproperties)Page).UCMaterialPropertiesSteel1;
                SteelTemplate currentTemplate = CurrentSteelTemplate;
                controlSteel.LoadDefaultSteelLabelValues(currentTemplate, TableTypeSteel.None , true);

                //lblMaterial.Visible = false;
                divViewSteel.Visible = true;
                divViewTimber.Visible = false;                
                divViewConcrete.Visible = false;
            }
            else if (materialTypeId == 3)
            {
                selection.SelectedTemplateType = TemplateType.TimberTemplate;

                materialpropertiestimber controlTimber = ((materialproperties)Page).UCMaterialPropertiesTimber1;
                TimberTemplate currentTemplate = CurrentTimberTemplate;
                controlTimber.LoadDefaultTimberLabelValues(currentTemplate, TableTypeTimber.None, true);

                //lblMaterial.Visible = false;
                divViewTimber.Visible = true;
                divViewConcrete.Visible = false;
                divViewSteel.Visible = false;                
            }

            UserSelection = selection;        
        }

        private void LoadCurrentSelection()
        {
            if (UserSelection.SelectedLocation != 0 & UserSelection.SelectedCES != 0 &
                UserSelection.SelectedFCM != 0 & UserSelection.SelectedYear != 0)
            {
                int materialTypeId = (int)UserSelection.SelectedTemplateType;
                drpMaterial.SelectedValue = Convert.ToString(materialTypeId);

                if (materialTypeId != 0)
                    ValidateMaterialSelection(materialTypeId);
            }            
        }

        #endregion

    }
}