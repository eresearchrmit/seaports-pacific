using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CCIMT.Presentation
{
    public partial class _Default : BasePage
    {

        #region Page Events

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                LoadYearDDL();
                LoadCurrentSelections();
            }
        }

        protected void cmdNext_Click(object sender, EventArgs e)
        {
            CurrentSelection currentSelection = new CurrentSelection();
            currentSelection.SelectedLocation = (Location)drpLocation.SelectedIndex;
            currentSelection.SelectedCES = (CarbonEmissionScenario)drpScenario.SelectedIndex;
            currentSelection.SelectedFCM = (FutureClimateModel)drpModel.SelectedIndex;
            currentSelection.SelectedYear = Convert.ToInt16(drpYear.SelectedItem.Value);            
            UserSelection = currentSelection;
            base.CurrentConcreteTemplate = null;
            base.CurrentSteelTemplate = null;
            base.CurrentTimberTemplate = null;
            Response.Redirect("materialproperties.aspx");
        }

        #endregion

        #region Private Methods

        //private bool ValidateControls()
        //{
        //    int validIndex = 0;

        //    if (String.Equals(ddlLocation.SelectedValue, "Please Select"))
        //    {
        //        lblValLocation.Visible = true;
        //    }
        //    else
        //    {
        //        validIndex++;
        //        lblValLocation.Visible = false;
        //    }

        //    if (String.Equals(ddlScenario.SelectedValue, "Please Select"))
        //    {
        //        lblValScenario.Visible = true;
        //    }
        //    else
        //    {
        //        validIndex++;
        //        lblValScenario.Visible = false;
        //    }

        //    if (String.Equals(ddlModel.SelectedValue, "Please Select"))
        //    {
        //        lblValModel.Visible = true;
        //    }
        //    else
        //    {
        //        validIndex++;
        //        lblValModel.Visible = false;
        //    }

        //    if (String.Equals(ddlYear.SelectedValue, "Please Select"))
        //    {
        //        lblValYear.Visible = true;
        //    }
        //    else
        //    {
        //        validIndex++;
        //        lblValYear.Visible = false;
        //    }

        //    if (validIndex == 4)
        //    {
        //        CurrentSelection currentSelection = new CurrentSelection();
        //        currentSelection.SelectedLocation = (Location)ddlLocation.SelectedIndex;
        //        currentSelection.SelectedCES = (CarbonEmissionScenario)ddlScenario.SelectedIndex;
        //        currentSelection.SelectedFCM = (FutureClimateModel)ddlModel.SelectedIndex;
        //        currentSelection.SelectedYear = Convert.ToInt16(ddlYear.SelectedItem.Value);
        //        UserSelection = currentSelection;

        //        return true;
        //    }
        //    else
        //    {
        //        return false;
        //    }
        //}

        private void LoadYearDDL()
        {
            for (int year = 2000; year <= 2070; year++)
            {
                ListItem yearItem = new ListItem();
                yearItem.Text = Convert.ToString(year);
                yearItem.Value = Convert.ToString(year);
                drpYear.Items.Add(yearItem);
            }

            // Default Year Item
            ListItem defaultItem = new ListItem("Please Select", "0");
            drpYear.Items.Insert(0, defaultItem);

            int currentYear = DateTime.Now.Year;
            drpYear.SelectedValue = Convert.ToString(currentYear);
        }

        private void LoadCurrentSelections()
        {
            if (UserSelection.SelectedLocation != 0 & UserSelection.SelectedCES != 0 &
                UserSelection.SelectedFCM != 0 & UserSelection.SelectedYear != 0)
            {
                drpLocation.SelectedValue = Convert.ToString((int)UserSelection.SelectedLocation);
                drpModel.SelectedValue = Convert.ToString((int)UserSelection.SelectedFCM);
                drpScenario.SelectedValue = Convert.ToString((int)UserSelection.SelectedCES);
                drpYear.SelectedValue = Convert.ToString((int)UserSelection.SelectedYear);

                DeselectTemplateType();
            }
        }

        private void DeselectTemplateType()
        {
            TemplateType noneSelect = TemplateType.None;
            CurrentSelection currentSelection = UserSelection;
            currentSelection.SelectedTemplateType = noneSelect;
            UserSelection = currentSelection;
        }

        #endregion        

    }
}
    