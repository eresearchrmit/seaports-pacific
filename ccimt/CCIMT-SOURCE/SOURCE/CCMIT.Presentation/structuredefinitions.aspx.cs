using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Excel = Microsoft.Office.Interop.Excel;
using System.Text;
using System.IO;
using System.Drawing;
using System.Text.RegularExpressions;

namespace CCIMT.Presentation
{
    public partial class structuredefinitions : BasePage
    {

        #region Public Properties

        public bool HideDetailConcrete { get; set; }
        public bool HideDetailTimber { get; set; }
        public bool HideDetailSteel { get; set; }

        #endregion

        #region Page Events

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CopyTemplateFileToObjectFolder();
                LoadSelectedMaterialData();
            }
        }

        protected void btnNext_Click(object sender, EventArgs e)
        {
            RunModel();
            Response.Redirect("climatechangeimpact.aspx");
        }

        protected void btnUploadInputData_Click(object sender, EventArgs e)
        {
            bool isErrorMessageExists = false;
            try
            {
                string filePath = String.Empty;
                SaveInputDataExcel(ref filePath);
                UploadInputData(filePath, ref isErrorMessageExists);
                DeleteInputDataExcel(filePath);
                LoadAssets();
            }
            catch (Exception ex)
            {
                lblInputDataMessage.Text = String.Empty;
                if (!isErrorMessageExists)
                    lblInputDataMessage.Text = ex.Message;
            }
        }

        #region Concrete Events

        protected void btnAddConcreteAsset_Click(object sender, EventArgs e)
        {
            bool isUpdate = false;
            if (String.Equals(((Button)sender).Text, Common.EVENT_NAME_UPDATE))
                isUpdate = true;

            if (isUpdate)
            {
                UpdateConcreteAsset();
            }
            else
            {
                AddConcreteAsset();
            }
        }

        protected void btnDeleteConcreteAsset_Click(object sender, ImageClickEventArgs e)
        {
            string assetCode = ((ImageButton)sender).CommandArgument;

            ConcreteTemplate concreteTemplate = CurrentConcreteTemplate;
            IList<ConcreteAsset> concreteAssets = concreteTemplate.ConcreteAssetList;

            ConcreteAsset selectedAsset = GetConcreteAssetByCode(assetCode, concreteAssets);

            if (!String.IsNullOrEmpty(selectedAsset.AssetCode))
            {
                concreteAssets.Remove(selectedAsset);
            }

            lblInputDataMessage.Text = String.Empty;
            concreteTemplate.ConcreteAssetList = concreteAssets;
            CurrentConcreteTemplate = concreteTemplate;
            LoadConcreteAssets();
        }

        protected void btnEditConcreteAsset_Click(object sender, ImageClickEventArgs e)
        {
            string assetCode = ((ImageButton)sender).CommandArgument;

            ConcreteTemplate concreteTemplate = CurrentConcreteTemplate;
            IList<ConcreteAsset> concreteAssets = concreteTemplate.ConcreteAssetList;

            ConcreteAsset selectedAsset = GetConcreteAssetByCode(assetCode, concreteAssets);

            txtFTAssetCode.Text = selectedAsset.AssetCode;
            txtFTAssetCode.ReadOnly = true;
            txtFTYearBuilt.Text = Convert.ToString(selectedAsset.YearBuilt);
            txtFTDescription.Text = selectedAsset.Description;
            ddlZone.SelectedValue = Convert.ToString((int)selectedAsset.Zone);
            txtFTDistFromCost.Text = Convert.ToString(selectedAsset.DistFromCost);
            ddlExposure.SelectedValue = Convert.ToString((int)selectedAsset.Exposure);
            ddlCarbonation.SelectedValue = Convert.ToString((int)selectedAsset.Carbonation);
            ddlChloride.SelectedValue = Convert.ToString((int)selectedAsset.Chloride);
            txtFTCover.Text = Convert.ToString(selectedAsset.Cover);
            txtFTDMember.Text = Convert.ToString(selectedAsset.DMember);
            txtFTF1C.Text = Convert.ToString(selectedAsset.F1C);
            txtFTWC.Text = Convert.ToString(selectedAsset.WC);
            txtFTCe.Text = Convert.ToString(selectedAsset.Ce);
            txtFTDBar.Text = Convert.ToString(selectedAsset.DBar);
            txtFTXcTo.Text = Convert.ToString(selectedAsset.XcTo);

            btnAddConcreteAsset.Text = Common.EVENT_NAME_UPDATE;
            lblInputDataMessage.Text = String.Empty;

            EnableAssetDeleteButton(((ImageButton)sender).ClientID, false);
        }

        protected void btnClearConcreteAsset_Click(object sender, EventArgs e)
        {
            HideDetailConcrete = false;
            ClearConcreteAsset();
            txtFTAssetCode.ReadOnly = false;
        }

        #endregion

        #region Timber Events

        protected void btnAddTimberAsset_Click(object sender, EventArgs e)
        {
            bool isUpdate = false;
            if (String.Equals(((Button)sender).Text, Common.EVENT_NAME_UPDATE))
                isUpdate = true;

            if (isUpdate)
            {
                UpdateTimberAsset();
            }
            else
            {
                AddTimberAsset();
            }
        }

        protected void btnDeleteTimberAsset_Click(object sender, ImageClickEventArgs e)
        {
            string assetCode = ((ImageButton)sender).CommandArgument;

            TimberTemplate timberTemplate = CurrentTimberTemplate;
            IList<TimberAsset> timberAssets = timberTemplate.TimberAssetList;

            TimberAsset selectedAsset = GetTimberAssetByCode(assetCode, timberAssets);

            if (!String.IsNullOrEmpty(selectedAsset.AssetCode))
            {
                timberAssets.Remove(selectedAsset);
            }

            lblInputDataMessage.Text = String.Empty;
            timberTemplate.TimberAssetList = timberAssets;
            CurrentTimberTemplate = timberTemplate;
            LoadTimberAssets();
        }

        protected void btnEditTimberAsset_Click(object sender, ImageClickEventArgs e)
        {
            string assetCode = ((ImageButton)sender).CommandArgument;

            TimberTemplate timberTemplate = CurrentTimberTemplate;
            IList<TimberAsset> timberAssets = timberTemplate.TimberAssetList;

            TimberAsset selectedAsset = GetTimberAssetByCode(assetCode, timberAssets);
            
            txtTAssetCode.Text = selectedAsset.AssetCode;
            txtTAssetCode.ReadOnly = true;
            txtTYearInstalled.Text = Convert.ToString(selectedAsset.YearInstalled);
            txtTDescription.Text = selectedAsset.Description;
            txtTType.Text = selectedAsset.Type;
            ddlTHW.SelectedValue = Convert.ToString((int)selectedAsset.HW);
            ddlTSW.SelectedValue = Convert.ToString((int)selectedAsset.SW);
            txtTDo.Text = Convert.ToString(selectedAsset.Do);
            txtTDSap.Text = Convert.ToString(selectedAsset.DSap);
            txtTDReplace.Text = Convert.ToString(selectedAsset.DReplace);
            ddlTMaintenance.SelectedValue = Convert.ToString((int)selectedAsset.Maintenance);
            ddlTContact.SelectedValue = Convert.ToString((int)selectedAsset.Contact);
            ddlTKnot.SelectedValue = Convert.ToString((int)selectedAsset.Knot);
            ddlTZone.SelectedValue = Convert.ToString((int)selectedAsset.Zone);
            ddlTExposure.SelectedValue = Convert.ToString((int)selectedAsset.Exposure);

            btnAddTimberAsset.Text = Common.EVENT_NAME_UPDATE;
            lblInputDataMessage.Text = String.Empty;

            EnableAssetDeleteButton(((ImageButton)sender).ClientID, false);
        }

        protected void btnClearTimberAsset_Click(object sender, EventArgs e)
        {
            HideDetailTimber = false;
            ClearTimberAsset();
            txtTAssetCode.ReadOnly = false;
        }

        #endregion

        #region Steel Events

        protected void btnAddSteelAsset_Click(object sender, EventArgs e)
        {
            bool isUpdate = false;
            if (String.Equals(((Button)sender).Text, Common.EVENT_NAME_UPDATE))
                isUpdate = true;

            if (isUpdate)
            {
                UpdateSteelAsset();
            }
            else
            {
                AddSteelAsset();
            }
        }

        protected void btnDeleteSteelAsset_Click(object sender, ImageClickEventArgs e)
        {
            string assetCode = ((ImageButton)sender).CommandArgument;

            SteelTemplate SteelTemplate = CurrentSteelTemplate;
            IList<SteelAsset> SteelAssets = SteelTemplate.SteelAssetList;

            SteelAsset selectedAsset = GetSteelAssetByCode(assetCode, SteelAssets);

            if (!String.IsNullOrEmpty(selectedAsset.AssetCode))
            {
                SteelAssets.Remove(selectedAsset);
            }

            lblInputDataMessage.Text = String.Empty;
            SteelTemplate.SteelAssetList = SteelAssets;
            CurrentSteelTemplate = SteelTemplate;
            LoadSteelAssets();
        }

        protected void btnEditSteelAsset_Click(object sender, ImageClickEventArgs e)
        {
            string assetCode = ((ImageButton)sender).CommandArgument;

            SteelTemplate SteelTemplate = CurrentSteelTemplate;
            IList<SteelAsset> SteelAssets = SteelTemplate.SteelAssetList;

            SteelAsset selectedAsset = GetSteelAssetByCode(assetCode, SteelAssets);

            txtSAssetCode.Text = selectedAsset.AssetCode;
            txtSAssetCode.ReadOnly = true;
            txtSYearBuilt.Text = Convert.ToString(selectedAsset.YearBuilt);
            txtSDescription.Text = selectedAsset.Description;
            ddlSZone.SelectedValue = Convert.ToString((int)selectedAsset.Zone);
            ddlSHazardZone.SelectedValue = Convert.ToString((int)selectedAsset.HazardZone);
            ddlSCoastalZone.SelectedValue = Convert.ToString((int)selectedAsset.CoastalZone);
            ddlSCoastalExposure.SelectedValue = Convert.ToString((int)selectedAsset.CoastClass);
            ddlSSiteClassification.SelectedValue = Convert.ToString((int)selectedAsset.SiteClass);
            txtSDistFromCost.Text = Convert.ToString(selectedAsset.DistFromCost);

            btnAddSteelAsset.Text = Common.EVENT_NAME_UPDATE;
            lblInputDataMessage.Text = String.Empty;

            EnableAssetDeleteButton(((ImageButton)sender).ClientID, false);
        }

        protected void btnClearSteelAsset_Click(object sender, EventArgs e)
        {
            HideDetailSteel = false;
            ClearSteelAsset();
            txtSAssetCode.ReadOnly = false;
        }

        #endregion

        #endregion

        #region Private Fields

        Excel.Application excelApp;
        Excel.Workbook excelWorkbook;
        Excel.Sheets allSheets;

        #region Common Invalid Lists

        IList<int> invalidAssetCodeList = new List<int>();
        IList<int> invalidYearbuiltList = new List<int>();
        IList<int> invalidDistCostList = new List<int>();

        #endregion

        #region Concrete Invalid Lists
                
        IList<int> invalidCoverList = new List<int>();
        IList<int> invalidDMemberList = new List<int>();
        IList<int> invalidF1CList = new List<int>();
        IList<int> invalidWCList = new List<int>();
        IList<int> invalidCEList = new List<int>();
        IList<int> invalidDBarList = new List<int>();
        IList<int> invalidXcToList = new List<int>();

        #endregion

        #region Timber Invalid Lists

        IList<int> invalidTDoList = new List<int>();
        IList<int> invalidTDSapList = new List<int>();
        IList<int> invalidTDReplaceList = new List<int>();

        #endregion

        #endregion

        #region Private Methods

        #region Common

        private void LoadSelectedMaterialData()
        {
            if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
            {
                divViewConcrete.Visible = true;
                lblMaterialType.Text = "CONCRETE";
                LoadConcreteAssets();
            }
            else if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
            {
                divViewTimber.Visible = true;
                lblMaterialType.Text = "TIMBER";                
                LoadTimberAssets();
            }
            else if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
            {
                lblMaterialType.Text = "STEEL";
                divViewSteel.Visible = true;
                LoadSteelAssets();
            }
        }

        private void RunModel()
        {
            CommonMethods methods = new CommonMethods();
            try
            {
                string path = Server.MapPath(String.Concat(Common.OBJECT_PATH, Session.SessionID, "/", GetObjectFileName()));

                excelApp = new Excel.Application();
                excelApp.Visible = false;
                excelApp.DisplayAlerts = false;

                excelWorkbook = excelApp.Workbooks.Open(path, 0, false, 5, "", "", false, Excel.XlPlatform.xlWindows, "", true, false, 0, true, false, false);
                allSheets = excelWorkbook.Worksheets;

                // Update the Values of the Excel Sheet
                UpdateExcelSheet(ref allSheets, ref methods);
                UpdateInputData(ref allSheets, ref methods);
                // Save all the Updates
                excelWorkbook.Save();

                // Run the Macro to run the model
                object[] excelArgs = new Object[] { Common.MACRO_NAME_COPY_SHEETS };
                RunMacro(excelArgs);
            }
            catch (Exception ex)
            {
            }
            finally
            {
                allSheets = null;
                if (excelWorkbook != null)
                {
                    excelWorkbook.Close(false, null, null);
                    excelWorkbook = null;
                }
                excelApp.Quit();
                excelApp = null;

                GC.WaitForPendingFinalizers();
                GC.Collect();
                GC.WaitForPendingFinalizers();
                GC.Collect();
            }
        }

        private void UpdateExcelSheet(ref Excel.Sheets allSheets, ref CommonMethods methods)
        {
            if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                UpdateConcreteExcelSheet(ref allSheets, ref methods);
            if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                UpdateTimberExcelSheet(ref allSheets, ref methods);
            if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
                UpdateSteelExcelSheet(ref allSheets, ref methods);
        }

        private void UpdateInputData(ref Excel.Sheets allSheets, ref CommonMethods methods)
        {
            if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                UpdateConcreteInputData (ref allSheets, ref methods);
            if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                UpdateTimberInputData(ref allSheets, ref methods);
            if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
                UpdateSteelInputData(ref allSheets, ref methods);
        }

        private void RunMacro(object[] excelArgs)
        {
            excelApp.GetType().InvokeMember("Run", System.Reflection.BindingFlags.Default | System.Reflection.BindingFlags.InvokeMethod, null, excelApp, excelArgs);
        }

        private void LoadAssets()
        {
            if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                LoadConcreteAssets();
            else if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                LoadTimberAssets();
            else if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
                LoadSteelAssets();
        }

        private void EnableAssetDeleteButton(string clientId, bool isEnable)
        {
            IList<string> clientIdContent = clientId.Split('_').ToList<string>();
            int rowId = Convert.ToInt16(clientIdContent[clientIdContent.Count - 1]);

            if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
            {
                ImageButton deleteButton = (ImageButton)lstAssetsConcrete.Controls[rowId].Controls[3];
                deleteButton.Visible = false;
            }
            else if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
            {
                ImageButton deleteButton = (ImageButton)lstAssetsSteel.Controls[rowId].Controls[3];
                deleteButton.Visible = false;
            }
            else if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
            {
                ImageButton deleteButton = (ImageButton)lstAssetsTimber.Controls[rowId].Controls[3];
                deleteButton.Visible = false;
            }
        }

        private void CopyTemplateFileToObjectFolder()
        {
            try
            {
                string folderName = Session.SessionID;
                string folderPath = Server.MapPath(String.Concat(Common.OBJECT_PATH, folderName));

                // Delete the contents of the session folder
                CommonMethods methods = new CommonMethods();
                methods.DeleteContainingFiles(folderPath);

                string templateFile = String.Empty;
                string objectFile = String.Empty;

                // Copy the specific template folder to the session folder
                if (UserSelection.SelectedLocation == Location.Gladstone & UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                {
                    templateFile = Server.MapPath(String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_CONCRETE_GLADSTONE));
                    objectFile = Server.MapPath(String.Concat(Common.OBJECT_PATH, folderName, "/", Common.TEMPLATE_CONCRETE_GLADSTONE));
                }
                else if (UserSelection.SelectedLocation == Location.PortKembla & UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                {
                    templateFile = Server.MapPath(String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_CONCRETE_PORTKEMBLA));
                    objectFile = Server.MapPath(String.Concat(Common.OBJECT_PATH, folderName, "/", Common.TEMPLATE_CONCRETE_PORTKEMBLA));
                }
                else if (UserSelection.SelectedLocation == Location.Sydney & UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                {
                    templateFile = Server.MapPath(String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_CONCRETE_SYDNEY));
                    objectFile = Server.MapPath(String.Concat(Common.OBJECT_PATH, folderName, "/", Common.TEMPLATE_CONCRETE_SYDNEY));
                }
                else if (UserSelection.SelectedLocation == Location.Gladstone & UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                {
                    templateFile = Server.MapPath(String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_TIMBER_GLADSTONE));
                    objectFile = Server.MapPath(String.Concat(Common.OBJECT_PATH, folderName, "/", Common.TEMPLATE_TIMBER_GLADSTONE));
                }
                else if (UserSelection.SelectedLocation == Location.PortKembla & UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                {
                    templateFile = Server.MapPath(String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_TIMBER_PORTKEMBLA));
                    objectFile = Server.MapPath(String.Concat(Common.OBJECT_PATH, folderName, "/", Common.TEMPLATE_TIMBER_PORTKEMBLA));
                }
                else if (UserSelection.SelectedLocation == Location.Sydney & UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                {
                    templateFile = Server.MapPath(String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_TIMBER_SYDNEY));
                    objectFile = Server.MapPath(String.Concat(Common.OBJECT_PATH, folderName, "/", Common.TEMPLATE_TIMBER_SYDNEY));
                }
                else if (UserSelection.SelectedLocation == Location.Gladstone & UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
                {
                    templateFile = Server.MapPath(String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_STEEL_GLADSTONE));
                    objectFile = Server.MapPath(String.Concat(Common.OBJECT_PATH, folderName, "/", Common.TEMPLATE_STEEL_GLADSTONE));
                }
                else if (UserSelection.SelectedLocation == Location.PortKembla & UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
                {
                    templateFile = Server.MapPath(String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_STEEL_PORTKEMBLA));
                    objectFile = Server.MapPath(String.Concat(Common.OBJECT_PATH, folderName, "/", Common.TEMPLATE_STEEL_PORTKEMBLA));
                }
                else if (UserSelection.SelectedLocation == Location.Sydney & UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
                {
                    templateFile = Server.MapPath(String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_STEEL_SYDNEY));
                    objectFile = Server.MapPath(String.Concat(Common.OBJECT_PATH, folderName, "/", Common.TEMPLATE_STEEL_SYDNEY));
                }

                File.Copy(templateFile, objectFile);
            }
            catch (Exception ex)
            {
            }
            finally
            {
            }
        }

        #region Input Data Upload Common

        private void UploadInputData(string filePath, ref bool isErrorMessageExists)
        {
            Excel.Worksheet assetSheet = null;
            Excel.Range rangeInputData = null;

            try
            {
                excelApp = new Excel.Application();
                excelApp.Visible = false;
                excelApp.DisplayAlerts = false;

                excelWorkbook = excelApp.Workbooks.Open(filePath, 0, true, 5, "", "", true, Excel.XlPlatform.xlWindows, "\t", false, false, 0, true, 1, 0);
                allSheets = excelWorkbook.Worksheets;

                assetSheet = (Excel.Worksheet)allSheets.get_Item(Common.WORK_SHEET_INPUT_DATA);
                rangeInputData = assetSheet.UsedRange;

                // Read the Excel and Store the corresponding Asset Values
                IList<ConcreteAsset> concreteAssetsList = new List<ConcreteAsset>();
                IList<TimberAsset> timberAssetsList = new List<TimberAsset>();
                IList<SteelAsset> steelAssetsList = new List<SteelAsset>();

                if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                    concreteAssetsList = LoadConcreteInputData(rangeInputData);
                else if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                    timberAssetsList = LoadTimberInputData(rangeInputData);
                else if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
                    steelAssetsList = LoadSteelInputData(rangeInputData);

                string validationStringMessage = GenerateValidationStringMessage();
                bool isAssetListEmpty = false;

                if (String.IsNullOrEmpty(validationStringMessage))
                {
                    if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                    {
                        ConcreteTemplate currentConcreteTemplate = UserSelection.ConcreteTemplate;
                        currentConcreteTemplate.ConcreteAssetList = concreteAssetsList;
                        isAssetListEmpty = (concreteAssetsList.Count == 0 ? true : false);
                    }
                    else if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                    {
                        TimberTemplate currentTimberTemplate = UserSelection.TimberTemplate;
                        currentTimberTemplate.TimberAssetList = timberAssetsList;
                        isAssetListEmpty = (timberAssetsList.Count == 0 ? true : false);
                    }
                    else if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
                    {
                        SteelTemplate currentSteelTemplate = UserSelection.SteelTemplate;
                        currentSteelTemplate.SteelAssetList = steelAssetsList;
                        isAssetListEmpty = (steelAssetsList.Count == 0 ? true : false);
                    }

                    if (isAssetListEmpty)
                    {
                        lblInputDataMessage.Text = "<b>Upload fail. No 'Input Data' found.</b>";
                        lblInputDataMessage.ForeColor = Color.Red;
                    }
                    else
                    {
                        lblInputDataMessage.Text = "<b>'Input Data' uploaded successfully.</b>";
                        lblInputDataMessage.ForeColor = Color.Green;
                    }
                }
                else
                {
                    isErrorMessageExists = true;
                    lblInputDataMessage.Text = validationStringMessage;
                    lblInputDataMessage.ForeColor = Color.Red;
                }

                assetSheet = null;
                allSheets = null;
                if (excelWorkbook != null)
                {
                    excelWorkbook.Close(false, null, null);
                    excelWorkbook = null;
                }
            }
            catch (System.Runtime.InteropServices.COMException ce)
            {
                if (ce.ErrorCode == -2146827284)
                {
                    // TO DO : lblMessage.Text = "Excel file is already opened.<br/> Please close the file and try again.";
                    // TO DO : Logger.LogError(ce);
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally
            {
                excelApp.Quit();
                excelApp = null;

                GC.WaitForPendingFinalizers();
                GC.Collect();
                GC.WaitForPendingFinalizers();
                GC.Collect();
            }
        }

        private string GenerateValidationStringMessage()
        {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.Append("<b>Invalid values added in following fields.</b><br />");
            bool isInvalid = false;

            if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
            {
                #region Concrete
                if (invalidAssetCodeList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Asset Code :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidAssetCodeList));
                }
                if (invalidYearbuiltList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Year built :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidYearbuiltList));
                }
                if (invalidDistCostList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Dist from coast :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidDistCostList));
                }
                if (invalidCoverList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Cover :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidCoverList));
                }
                if (invalidDMemberList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>D<sub>member</sub> :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidDMemberList));
                }
                if (invalidF1CList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>f<sup>1</sup><sub>c</sub> :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidF1CList));
                }
                if (invalidWCList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>w/c :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidWCList));
                }
                if (invalidCEList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>C<sub>e</sub> :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidCEList));
                }
                if (invalidDBarList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>D<sub>bar</sub> :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidDBarList));
                }
                if (invalidXcToList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>x<sub>c</sub>(t<sub>o</sub>) :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidXcToList));
                }
                #endregion
            }
            else if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
            {
                #region Timber
                if (invalidAssetCodeList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Asset Code :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidAssetCodeList));
                }
                if (invalidYearbuiltList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Year installed :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidYearbuiltList));
                }
                if (invalidTDoList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>D<sub>o</sub> :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidTDoList));
                }
                if (invalidTDSapList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>D<sub>sap</sub> :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidTDSapList));
                }
                if (invalidTDReplaceList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>D<sub>replace</sub> :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidTDReplaceList));
                }
                #endregion
            }
            else if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
            {
                #region Steel
                if (invalidAssetCodeList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Asset Code :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidAssetCodeList));
                }
                if (invalidYearbuiltList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Year built :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidYearbuiltList));
                }
                if (invalidDistCostList.Count != 0)
                {
                    isInvalid = true;
                    errorMessage.Append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Dist from coast :</b> ");
                    errorMessage.Append(GenerateInvalidString(invalidDistCostList));
                }
                #endregion
            }

            if (!isInvalid)
                return String.Empty;
            else
                return errorMessage.ToString();
        }

        private string GenerateInvalidString(IList<int> rowNumberList)
        {
            string invalidRowString = String.Empty;
            foreach (int rowNo in rowNumberList)
            {
                invalidRowString = String.Concat(invalidRowString, rowNo, ",");
            }
            invalidRowString = invalidRowString.Substring(0, (invalidRowString.Length - 1));
            return String.Concat(invalidRowString, Common.LINE_BREAK);
        }

        private void SaveInputDataExcel(ref string filePath)
        {
            StringBuilder sbFilePath = new StringBuilder();
            sbFilePath.Append(Server.MapPath(Common.INPUTDATA_PATH));
            sbFilePath.Append(Session.SessionID);
            sbFilePath.Append("_");
            sbFilePath.Append(Guid.NewGuid().ToString());
            sbFilePath.Append(".xls");
            filePath = sbFilePath.ToString();
            FileUpload.PostedFile.SaveAs(filePath);
        }

        private void DeleteInputDataExcel(string filePath)
        {
            try
            {
                File.Delete(filePath);
            }
            catch (Exception ex)
            {
            }
        }

        private bool IsExcelRowEmptty(string assetCode, string description, int rowIndex)
        {
            bool isEmptyRow = false;

            if (!String.Equals(assetCode, Common.ERROR))
            {
                if (String.IsNullOrEmpty(assetCode))
                    isEmptyRow = true;

                if (isEmptyRow)
                {
                    if (!String.IsNullOrEmpty(description))
                    {
                        isEmptyRow = false;
                        invalidAssetCodeList.Add(rowIndex - 1);
                    }
                }
            }

            return isEmptyRow;
        }

        #endregion

        #endregion

        #region Private Concrete Methods

        #region Common

        /// <summary>
        /// This method is used to retrieve the Concrete asset list from session and bind to the list
        /// </summary>
        private void LoadConcreteAssets()
        {
            if (CurrentConcreteTemplate.ConcreteAssetList != null)
            {
                lstAssetsConcrete.DataSource = CurrentConcreteTemplate.ConcreteAssetList;
                lstAssetsConcrete.DataBind();
            }         

            divGridConcrete.Style["display"] = CurrentConcreteTemplate.ConcreteAssetList != null && CurrentConcreteTemplate.ConcreteAssetList.Count > 0 ? String.Empty : "none";
            btnNext.Visible = CurrentConcreteTemplate.ConcreteAssetList != null && CurrentConcreteTemplate.ConcreteAssetList.Count > 0;
            HideDetailConcrete = CurrentConcreteTemplate.ConcreteAssetList != null && CurrentConcreteTemplate.ConcreteAssetList.Count > 0;
        }

        /// <summary>
        /// This method is used to check wether a Concrete asset exists with a given Asset Code
        /// </summary>
        /// <param name="assetList"></param>
        /// <param name="assetCode"></param>
        /// <returns></returns>
        private bool IsAssetAlreadyExists(IList<ConcreteAsset> assetList, string assetCode)
        {
            if (assetList != null)
            {
                foreach (ConcreteAsset asset in assetList)
                {
                    if (String.Equals(asset.AssetCode, assetCode))
                        return true;
                }
            }

            return false;
        }

        /// <summary>
        /// This method is used to retrieve the particular Concrete asset from the session by the AssetCode
        /// </summary>
        /// <param name="assetCode"></param>
        /// <param name="assetList"></param>
        /// <returns></returns>
        private ConcreteAsset GetConcreteAssetByCode(string assetCode, IList<ConcreteAsset> assetList)
        {
            ConcreteAsset selectedAsset = new ConcreteAsset();

            foreach (ConcreteAsset asset in assetList)
            {
                if (String.Equals(asset.AssetCode, assetCode))
                {
                    selectedAsset = asset;
                }
            }

            return selectedAsset;
        }

        /// <summary>
        /// This method is used to retrieve the values from input controls and create a new 
        /// Concrete asset using them and add it to the session
        /// </summary>
        private void AddConcreteAsset()
        {
            ConcreteTemplate concreteTemplate = CurrentConcreteTemplate;
            IList<ConcreteAsset> concreteAssets = concreteTemplate.ConcreteAssetList;

            if (concreteAssets == null)
                concreteAssets = new List<ConcreteAsset>();

            string assetCode = txtFTAssetCode.Text;

            if (!IsAssetAlreadyExists(concreteAssets, assetCode))
            {
                int yearBuilt = Convert.ToInt16(txtFTYearBuilt.Text);
                string description = txtFTDescription.Text;
                string zone = ddlZone.SelectedItem.Text;
                decimal distFromCost = Convert.ToDecimal(txtFTDistFromCost.Text);
                string exposure = ddlExposure.SelectedItem.Text;
                string carbonation = ddlCarbonation.SelectedItem.Text;
                string chloride = ddlChloride.SelectedItem.Text;
                string p = "Disabled";
                int cover = Convert.ToInt16(txtFTCover.Text);
                int dMember = Convert.ToInt16(txtFTDMember.Text);
                int f1c = Convert.ToInt16(txtFTF1C.Text);
                decimal wc = Convert.ToDecimal(txtFTWC.Text);
                int ce = Convert.ToInt16(txtFTCe.Text);
                int dBar = Convert.ToInt16(txtFTDBar.Text);
                decimal xcTo = Convert.ToDecimal(txtFTXcTo.Text);

                ConcreteAsset asset = CommonMethods.CreateConcreteAsset(assetCode, yearBuilt, description, zone,
                    distFromCost, exposure, carbonation, chloride, p, cover, dMember, f1c, wc, ce, dBar, xcTo, true);
                concreteAssets.Add(asset);

                concreteTemplate.ConcreteAssetList = concreteAssets;
                CurrentConcreteTemplate = concreteTemplate;
                ClearConcreteAsset();
                LoadConcreteAssets();
            }
            else
            {
                string alert = "<script type=\"text/javascript\">alert(\"Asset Code already exists. <br /> Please try a different Asset Code.\")</script>";
                ClientScript.RegisterStartupScript(typeof(Page), "AssetDuplication", alert);
            }
        }

        /// <summary>
        /// This method is used to update the particular Concrete asset with values entered to the input controls
        /// </summary>
        private void UpdateConcreteAsset()
        {
            ConcreteTemplate concreteTemplate = CurrentConcreteTemplate;
            IList<ConcreteAsset> concreteAssets = concreteTemplate.ConcreteAssetList;

            string assetCode = txtFTAssetCode.Text;
            ConcreteAsset selectedAsset = GetConcreteAssetByCode(assetCode, concreteAssets);
            concreteAssets.Remove(selectedAsset);

            selectedAsset.YearBuilt = Convert.ToInt16(txtFTYearBuilt.Text);
            selectedAsset.Description = txtFTDescription.Text;
            selectedAsset.Zone = (Zone)Enum.Parse(typeof(Zone), ddlZone.SelectedItem.Text);
            selectedAsset.DistFromCost = Convert.ToDecimal(txtFTDistFromCost.Text);
            selectedAsset.Exposure = (Exposure)Enum.Parse(typeof(Exposure), ddlExposure.SelectedItem.Text);
            selectedAsset.Carbonation = (Carbonation)Enum.Parse(typeof(Carbonation), ddlCarbonation.SelectedItem.Text);
            selectedAsset.Chloride = (Chloride)Enum.Parse(typeof(Chloride), ddlChloride.SelectedItem.Text);
            selectedAsset.Cover = Convert.ToInt16(txtFTCover.Text);
            selectedAsset.DMember = Convert.ToInt16(txtFTDMember.Text);
            selectedAsset.F1C = Convert.ToInt16(txtFTF1C.Text);
            selectedAsset.WC = Convert.ToDecimal(txtFTWC.Text);
            selectedAsset.Ce = Convert.ToInt16(txtFTCe.Text);
            selectedAsset.DBar = Convert.ToInt16(txtFTDBar.Text);
            selectedAsset.XcTo = Convert.ToDecimal(txtFTXcTo.Text);

            concreteAssets.Add(selectedAsset);
            concreteTemplate.ConcreteAssetList = concreteAssets;
            CurrentConcreteTemplate = concreteTemplate;

            txtFTAssetCode.ReadOnly = false;
            btnAddConcreteAsset.Text = Common.EVENT_NAME_SAVE;
            ClearConcreteAsset();
            LoadConcreteAssets();
            HideDetailConcrete = true;
        }

        /// <summary>
        /// This method is used to clear Concrete asset details from the footer input controls
        /// </summary>
        private void ClearConcreteAsset()
        {
            if (String.Equals(btnAddConcreteAsset.Text, Common.EVENT_NAME_UPDATE))
            {
                LoadConcreteAssets();
                HideDetailConcrete = false;
            }

            txtFTAssetCode.Text = String.Empty;
            txtFTYearBuilt.Text = String.Empty;
            txtFTDescription.Text = String.Empty;
            ddlZone.SelectedValue = Common.ZERO_VALUE;
            txtFTDistFromCost.Text = String.Empty;
            ddlExposure.SelectedValue = Common.ZERO_VALUE;
            ddlCarbonation.SelectedValue = Common.ZERO_VALUE;
            ddlChloride.SelectedValue = Common.ZERO_VALUE;
            txtFTCover.Text = String.Empty;
            txtFTDMember.Text = String.Empty;
            txtFTF1C.Text = String.Empty;
            txtFTWC.Text = String.Empty;
            txtFTCe.Text = String.Empty;
            txtFTDBar.Text = String.Empty;
            txtFTXcTo.Text = String.Empty;
            btnAddConcreteAsset.Text = Common.EVENT_NAME_SAVE;
        }

        #endregion

        #region Coefficients Cell Updates

        /// <summary>
        /// This method is used to update the Concrete excel sheet Asset details with the new values entered
        /// </summary>
        /// <param name="allSheets"></param>
        /// <param name="methods"></param>
        private void UpdateConcreteExcelSheet(ref Excel.Sheets allSheets, ref CommonMethods methods)
        {
            Excel.Worksheet coefficientsSheet = (Excel.Worksheet)allSheets.get_Item(Common.WORK_SHEET_COEFFICIENTS);
            ConcreteTemplate template = UserSelection.ConcreteTemplate;

            UpdateChlorideCorrosionRates20C(template, methods, ref coefficientsSheet);
            UpdateCarbonationCorrosionRates20C(template, methods, ref coefficientsSheet);
            UpdateCO2DiffusionCoefficient(template, methods, ref coefficientsSheet);
            UpdateActivationEnergy(template, methods, ref coefficientsSheet);
            UpdateSurfaceChlorideConcentration(template, methods, ref coefficientsSheet);
            UpdateChlorideDiffusionCoefficient(template, methods, ref coefficientsSheet);
            UpdateLegends(template, methods, ref coefficientsSheet);
        }

        private void UpdateChlorideCorrosionRates20C(ConcreteTemplate template, CommonMethods methods, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isCarbCR20Updated)
            {
                coefficientSheet.Cells[4, "G"] = template.CB1Mean;
                coefficientSheet.Cells[4, "H"] = template.CB1SD;
                coefficientSheet.Cells[5, "G"] = template.CB2Mean;
                coefficientSheet.Cells[5, "H"] = template.CB2SD;
                coefficientSheet.Cells[6, "G"] = template.CB3Mean;
                coefficientSheet.Cells[6, "H"] = template.CB3SD;
                coefficientSheet.Cells[7, "G"] = template.CB4Mean;
                coefficientSheet.Cells[7, "H"] = template.CB4SD;
            }
        }

        private void UpdateCarbonationCorrosionRates20C(ConcreteTemplate template, CommonMethods methods, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isChlCR20Updated)
            {
                coefficientSheet.Cells[12, "G"] = template.CL1Mean;
                coefficientSheet.Cells[12, "H"] = template.CL1SD;
                coefficientSheet.Cells[13, "G"] = template.CL2Mean;
                coefficientSheet.Cells[13, "H"] = template.CL2SD;
                coefficientSheet.Cells[14, "G"] = template.CL3Mean;
                coefficientSheet.Cells[14, "H"] = template.CL3SD;
                coefficientSheet.Cells[15, "G"] = template.CL4Mean;
                coefficientSheet.Cells[15, "H"] = template.CL4SD;
                coefficientSheet.Cells[16, "G"] = template.CL5Mean;
                coefficientSheet.Cells[16, "H"] = template.CL5SD;
            }
        }

        private void UpdateCO2DiffusionCoefficient(ConcreteTemplate template, CommonMethods methods, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isCO2DCUpdated)
            {
                coefficientSheet.Cells[3, "L"] = template.coVal30D1;
                coefficientSheet.Cells[3, "M"] = template.coVal30ND;
                coefficientSheet.Cells[4, "L"] = template.coVal35D1;
                coefficientSheet.Cells[4, "M"] = template.coVal35ND;
                coefficientSheet.Cells[5, "L"] = template.coVal40D1;
                coefficientSheet.Cells[5, "M"] = template.coVal40ND;
                coefficientSheet.Cells[6, "L"] = template.coVal45D1;
                coefficientSheet.Cells[6, "M"] = template.coVal45ND;
                coefficientSheet.Cells[7, "L"] = template.coVal50D1;
                coefficientSheet.Cells[7, "M"] = template.coVal50ND;
                coefficientSheet.Cells[8, "L"] = template.coVal55D1;
                coefficientSheet.Cells[8, "M"] = template.coVal55ND;
                coefficientSheet.Cells[9, "L"] = template.coVal60D1;
                coefficientSheet.Cells[9, "M"] = template.coVal60ND;
                coefficientSheet.Cells[10, "L"] = template.coVal65D1;
                coefficientSheet.Cells[10, "M"] = template.coVal65ND;
            }
        }

        private void UpdateActivationEnergy(ConcreteTemplate template, CommonMethods methods, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isAEUpdated)
            {
                coefficientSheet.Cells[3, "P"] = template.aeVal3E;
                coefficientSheet.Cells[4, "P"] = template.aeVal4E;
                coefficientSheet.Cells[5, "P"] = template.aeVal5E;
                coefficientSheet.Cells[6, "P"] = template.aeVal6E;
                coefficientSheet.Cells[7, "P"] = template.aeVal7E;
            }
        }

        private void UpdateSurfaceChlorideConcentration(ConcreteTemplate template, CommonMethods methods, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isSCCUpdated)
            {
                coefficientSheet.Cells[3, "T"] = template.sccTidalSplashMean;
                coefficientSheet.Cells[4, "T"] = template.sccAtmNearCoastMean;
                coefficientSheet.Cells[5, "T"] = template.sccAtmFarCoastMean;
            }
        }

        private void UpdateChlorideDiffusionCoefficient(ConcreteTemplate template, CommonMethods methods, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isCDCUpdated)
            {
                coefficientSheet.Cells[3, "W"] = template.cdcVal40FC;
                coefficientSheet.Cells[3, "X"] = template.cdcVal40DC;
                coefficientSheet.Cells[4, "W"] = template.cdcVal45FC;
                coefficientSheet.Cells[4, "X"] = template.cdcVal45DC;
                coefficientSheet.Cells[5, "W"] = template.cdcVal50FC;
                coefficientSheet.Cells[5, "X"] = template.cdcVal50DC;
                coefficientSheet.Cells[6, "W"] = template.cdcVal55FC;
                coefficientSheet.Cells[6, "X"] = template.cdcVal55DC;
            }
        }

        private void UpdateLegends(ConcreteTemplate template, CommonMethods methods, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isLegendsUpdated)
            {
                coefficientSheet.Cells[5, "B"] = template.legendKUrban;
                coefficientSheet.Cells[6, "B"] = template.legendNM;
            }
        }

        #endregion

        #region Input Data Upload / Save

        /// <summary>
        /// This method is used to update the excel cells according to the Input data upload sheet
        /// </summary>
        /// <param name="allSheets"></param>
        /// <param name="methods"></param>
        private void UpdateConcreteInputData(ref Excel.Sheets allSheets, ref CommonMethods methods)
        {
            Excel.Worksheet inputDataSheet = (Excel.Worksheet)allSheets.get_Item(Common.WORK_SHEET_INPUT_DATA);

            ConcreteTemplate template = UserSelection.ConcreteTemplate;
            int assetStartRowIndex = Common.ASSET_START_ROW_INDEX;

            foreach (ConcreteAsset asset in template.ConcreteAssetList)
            {
                if (asset.IsEdited)
                {
                    inputDataSheet.Cells[assetStartRowIndex, "A"] = asset.AssetCode;
                    inputDataSheet.Cells[assetStartRowIndex, "B"] = asset.YearBuilt;
                    inputDataSheet.Cells[assetStartRowIndex, "C"] = asset.Description;
                    inputDataSheet.Cells[assetStartRowIndex, "D"] = Enum.GetName(typeof(Zone), asset.Zone);
                    inputDataSheet.Cells[assetStartRowIndex, "E"] = asset.DistFromCost;
                    inputDataSheet.Cells[assetStartRowIndex, "F"] = Enum.GetName(typeof(Exposure), asset.Exposure);
                    inputDataSheet.Cells[assetStartRowIndex, "G"] = Enum.GetName(typeof(Carbonation), asset.Carbonation);
                    inputDataSheet.Cells[assetStartRowIndex, "H"] = Enum.GetName(typeof(Chloride), asset.Chloride);
                    inputDataSheet.Cells[assetStartRowIndex, "I"] = asset.P;
                    inputDataSheet.Cells[assetStartRowIndex, "J"] = asset.Cover;
                    inputDataSheet.Cells[assetStartRowIndex, "K"] = asset.DMember;
                    inputDataSheet.Cells[assetStartRowIndex, "L"] = asset.F1C;
                    inputDataSheet.Cells[assetStartRowIndex, "M"] = asset.WC;
                    inputDataSheet.Cells[assetStartRowIndex, "N"] = asset.Ce;
                    inputDataSheet.Cells[assetStartRowIndex, "O"] = asset.DBar;
                    inputDataSheet.Cells[assetStartRowIndex, "P"] = asset.XcTo;
                }

                assetStartRowIndex++;
            }
        }

        /// <summary>
        /// This method is used to load the input data according to the upload excel sheet
        /// </summary>
        /// <param name="range"></param>
        /// <returns></returns>
        private IList<ConcreteAsset> LoadConcreteInputData(Excel.Range range)
        {
            IList<ConcreteAsset> concreteAssets = new List<ConcreteAsset>();

            int rowIndex = Common.CONCRETE_UPLOAD_ASSET_START_ROW_INDEX;
            string assetCode = String.Empty;
            string description = String.Empty;
            IList<ConcreteAsset> concreteAssetList = new List<ConcreteAsset>();
            bool isErrorExists = false;

            do
            {
                assetCode = ValidateAssetCode((range.Cells[rowIndex, 1] as Excel.Range).Value2, rowIndex, ref isErrorExists);
                string yearBuiltStr = Convert.ToString((range.Cells[rowIndex, 2] as Excel.Range).Value2);
                description = Convert.ToString((range.Cells[rowIndex, 3] as Excel.Range).Value2);

                if (!String.IsNullOrEmpty(assetCode) & !String.IsNullOrEmpty(yearBuiltStr) & !String.IsNullOrEmpty(description))
                {
                    int yearBuilt = ValidateYearbuilt(yearBuiltStr, rowIndex, ref isErrorExists);
                    string zone = Convert.ToString((range.Cells[rowIndex, 4] as Excel.Range).Value2);
                    decimal distFromCost = ValidateDistFromCost((range.Cells[rowIndex, 5] as Excel.Range).Value2, rowIndex, ref isErrorExists);
                    string exposure = Convert.ToString((range.Cells[rowIndex, 6] as Excel.Range).Value2);
                    string carbonation = Convert.ToString((range.Cells[rowIndex, 7] as Excel.Range).Value2);
                    string chloride = Convert.ToString((range.Cells[rowIndex, 8] as Excel.Range).Value2);
                    string p = Convert.ToString((range.Cells[rowIndex, 9] as Excel.Range).Value2);
                    int cover = ValidateCover((range.Cells[rowIndex, 10] as Excel.Range).Value2, rowIndex, ref isErrorExists);
                    int dMember = ValidateDMember((range.Cells[rowIndex, 11] as Excel.Range).Value2, rowIndex, ref isErrorExists);
                    int f1c = ValidateF1C((range.Cells[rowIndex, 12] as Excel.Range).Value2, rowIndex, ref isErrorExists);
                    decimal wc = ValidateWC((range.Cells[rowIndex, 13] as Excel.Range).Value2, rowIndex, ref isErrorExists);
                    int ce = ValidateCE((range.Cells[rowIndex, 14] as Excel.Range).Value2, rowIndex, ref isErrorExists);
                    int dBar = ValidateDBar((range.Cells[rowIndex, 15] as Excel.Range).Value2, rowIndex, ref isErrorExists);
                    decimal xcTo = ValidateXcTo((range.Cells[rowIndex, 16] as Excel.Range).Value2, rowIndex, ref isErrorExists);

                    if (!isErrorExists)
                    {
                        ConcreteAsset asset = CommonMethods.CreateConcreteAsset(assetCode, yearBuilt, description, zone, distFromCost,
                            exposure, carbonation, chloride, p, cover, dMember, f1c, wc, ce, dBar, xcTo, true);

                        concreteAssetList.Add(asset);
                    }
                }

                rowIndex++;
            }
            while (!IsExcelRowEmptty(assetCode, description, rowIndex));

            return concreteAssetList;
        }

        #endregion

        #region Excel : Input Data Validations

        private string ValidateAssetCode(object excelField, int rowNo, ref bool isErrorExists)
        {
            string assetCodeStr = Convert.ToString(excelField);
            Match match = Regex.Match(assetCodeStr, @"^[a-zA-Z0-9]+$", RegexOptions.IgnoreCase);

            if (!String.IsNullOrEmpty(assetCodeStr))
            {
                if (!match.Success)
                {
                    assetCodeStr = Common.ERROR;
                    invalidAssetCodeList.Add(rowNo);
                    isErrorExists = true;
                }
            }

            return assetCodeStr;
        }

        private int ValidateYearbuilt(object excelField, int rowNo, ref bool isErrorExists)
        {
            Int16 yearbuilt = 0;
            string yearbuiltStr = Convert.ToString(excelField);
            Int16.TryParse(yearbuiltStr, out yearbuilt);

            if (!String.Equals(yearbuilt, Common.ZERO_VALUE))
            {
                if (yearbuilt == 0)
                {
                    invalidYearbuiltList.Add(rowNo);
                    isErrorExists = true;
                }
            }

            return yearbuilt;
        }

        private decimal ValidateDistFromCost(object excelField, int rowNo, ref bool isErrorExists)
        {
            decimal distFromCost = 0;
            string distFromCostStr = Convert.ToString(excelField);
            decimal.TryParse(distFromCostStr, out distFromCost);

            if (!String.Equals(distFromCostStr, Common.ZERO_VALUE))
            {
                if (distFromCost == 0)
                {
                    invalidDistCostList.Add(rowNo);
                    isErrorExists = true;
                }
            }

            return distFromCost;
        }

        private int ValidateCover(object excelField, int rowNo, ref bool isErrorExists)
        {
            Int16 cover = 0;
            string coverStr = Convert.ToString(excelField);
            Int16.TryParse(coverStr, out cover);

            if (!String.Equals(coverStr, Common.ZERO_VALUE))
            {
                if (cover == 0)
                {
                    invalidCoverList.Add(rowNo);
                    isErrorExists = true;
                }
            }

            return cover;
        }

        private int ValidateDMember(object excelField, int rowNo, ref bool isErrorExists)
        {
            Int16 dMember = 0;
            string dMemberStr = Convert.ToString(excelField);
            Int16.TryParse(dMemberStr, out dMember);

            if (!String.Equals(dMemberStr, Common.ZERO_VALUE))
            {
                if (dMember == 0)
                {
                    invalidDMemberList.Add(rowNo);
                    isErrorExists = true;
                }
            }

            return dMember;
        }

        private int ValidateF1C(object excelField, int rowNo, ref bool isErrorExists)
        {
            Int16 f1c = 0;
            string f1cStr = Convert.ToString(excelField);
            Int16.TryParse(f1cStr, out f1c);

            if (!String.Equals(f1cStr, Common.ZERO_VALUE))
            {
                if (f1c == 0)
                {
                    invalidF1CList.Add(rowNo);
                    isErrorExists = true;
                }
            }

            return f1c;
        }

        private decimal ValidateWC(object excelField, int rowNo, ref bool isErrorExists)
        {
            decimal wc = 0;
            string wcStr = Convert.ToString(excelField);
            decimal.TryParse(wcStr, out wc);

            if (!String.Equals(wcStr, Common.ZERO_VALUE))
            {
                if (wc == 0)
                {
                    invalidWCList.Add(rowNo);
                    isErrorExists = true;
                }
            }

            return wc;
        }

        private int ValidateCE(object excelField, int rowNo, ref bool isErrorExists)
        {
            Int16 ce = 0;
            string ceStr = Convert.ToString(excelField);
            Int16.TryParse(ceStr, out ce);

            if (!String.Equals(ceStr, Common.ZERO_VALUE))
            {
                if (ce == 0)
                {
                    invalidCEList.Add(rowNo);
                    isErrorExists = true;
                }
            }

            return ce;
        }

        private int ValidateDBar(object excelField, int rowNo, ref bool isErrorExists)
        {
            Int16 dBar = 0;
            string dBarStr = Convert.ToString(excelField);
            Int16.TryParse(dBarStr, out dBar);

            if (!String.Equals(dBarStr, Common.ZERO_VALUE))
            {
                if (dBar == 0)
                {
                    invalidDBarList.Add(rowNo);
                    isErrorExists = true;
                }
            }

            return dBar;
        }

        private decimal ValidateXcTo(object excelField, int rowNo, ref bool isErrorExists)
        {
            decimal xcTo = 0;
            string xcToStr = Convert.ToString(excelField);
            decimal.TryParse(xcToStr, out xcTo);

            if (!String.Equals(xcToStr, Common.ZERO_VALUE))
            {
                if (xcTo == 0)
                {
                    invalidXcToList.Add(rowNo);
                    isErrorExists = true;
                }
            }

            return xcTo;
        }

        #endregion

        #endregion

        #region Private Timber Methods

        #region Common

        /// <summary>
        /// This method is used to retrieve the Timber asset list from session and bind to the list
        /// </summary>
        private void LoadTimberAssets()
        {
            if (CurrentTimberTemplate.TimberAssetList != null)
            {
                lstAssetsTimber.DataSource = CurrentTimberTemplate.TimberAssetList;
                lstAssetsTimber.DataBind();
            }

            divGridTimber.Style["display"] = CurrentTimberTemplate.TimberAssetList != null && CurrentTimberTemplate.TimberAssetList.Count > 0 ? String.Empty : "none";
            btnNext.Visible = CurrentTimberTemplate.TimberAssetList != null && CurrentTimberTemplate.TimberAssetList.Count > 0;
            HideDetailTimber = CurrentTimberTemplate.TimberAssetList != null && CurrentTimberTemplate.TimberAssetList.Count > 0;
        }

        /// <summary>
        /// This method is used to check wether a Timber asset exists with a given Asset Code
        /// </summary>
        /// <param name="assetList"></param>
        /// <param name="assetCode"></param>
        /// <returns></returns>
        private bool IsTimberAssetAlreadyExists(IList<TimberAsset> assetList, string assetCode)
        {
            if (assetList != null)
            {
                foreach (TimberAsset asset in assetList)
                {
                    if (String.Equals(asset.AssetCode, assetCode))
                        return true;
                }
            }

            return false;
        }

        /// <summary>
        /// This method is used to retrieve the particular Timber asset from the session by the AssetCode
        /// </summary>
        /// <param name="assetCode"></param>
        /// <param name="assetList"></param>
        /// <returns></returns>
        private TimberAsset GetTimberAssetByCode(string assetCode, IList<TimberAsset> assetList)
        {
            TimberAsset selectedAsset = new TimberAsset();

            foreach (TimberAsset asset in assetList)
            {
                if (String.Equals(asset.AssetCode, assetCode))
                {
                    selectedAsset = asset;
                }
            }

            return selectedAsset;
        }

        /// <summary>
        /// This method is used to retrieve the values from input controls and create a new 
        /// Timber asset using them and add it to the session
        /// </summary>
        private void AddTimberAsset()
        {
            TimberTemplate timberTemplate = CurrentTimberTemplate;
            IList<TimberAsset> timberAssets = timberTemplate.TimberAssetList;

            if (timberAssets == null)
                timberAssets = new List<TimberAsset>();

            string assetCode = txtTAssetCode.Text;

            if (!IsTimberAssetAlreadyExists(timberAssets, assetCode))
            {
                int yearInstalled = Convert.ToInt16(txtTYearInstalled.Text);
                string description = txtTDescription.Text;
                string type = txtTType.Text;
                string hw = ddlTHW.SelectedItem.Text;
                string sw = ddlTSW.SelectedItem.Text;
                int doValue = Convert.ToInt16(txtTDo.Text);
                int dsap = Convert.ToInt16(txtTDSap.Text);
                int dreplace = Convert.ToInt16(txtTDReplace.Text);
                string maintenance = ddlTMaintenance.SelectedItem.Text;
                string contact = ddlTContact.SelectedItem.Text;
                string knot = ddlTKnot.SelectedItem.Text;
                string zone = ddlTZone.SelectedItem.Text;
                string exposure = ddlTExposure.SelectedItem.Text;

                TimberAsset asset = CommonMethods.CreateTimberAsset(assetCode, yearInstalled, description,
                    type, hw, sw, doValue, dsap, dreplace, maintenance, contact, knot, zone, exposure, true);
                timberAssets.Add(asset);

                timberTemplate.TimberAssetList = timberAssets;
                CurrentTimberTemplate = timberTemplate;
                ClearTimberAsset();
                LoadTimberAssets();
            }
            else
            {
                string alert = "<script type=\"text/javascript\">alert(\"Asset Code already exists. <br /> Please try a different Asset Code.\")</script>";
                ClientScript.RegisterStartupScript(typeof(Page), "AssetDuplication", alert);
            }
        }

        /// <summary>
        /// This method is used to update the particular Timber asset with values entered to the input controls
        /// </summary>
        private void UpdateTimberAsset()
        {
            TimberTemplate timberTemplate = CurrentTimberTemplate;
            IList<TimberAsset> timberAssets = timberTemplate.TimberAssetList;

            string assetCode = txtTAssetCode.Text;
            TimberAsset selectedAsset = GetTimberAssetByCode(assetCode, timberAssets);
            timberAssets.Remove(selectedAsset);

            selectedAsset.YearInstalled = Convert.ToInt16(txtTYearInstalled.Text);
            selectedAsset.Description = txtTDescription.Text;
            selectedAsset.Type = txtTType.Text;
            selectedAsset.HW = (TimberHW)Enum.Parse(typeof(TimberHW), ddlTHW.SelectedItem.Text);
            selectedAsset.SW = (TimberSW)Enum.Parse(typeof(TimberSW), ddlTSW.SelectedItem.Text);
            selectedAsset.Do = Convert.ToInt16(txtTDo.Text);
            selectedAsset.DSap = Convert.ToInt16(txtTDSap.Text);
            selectedAsset.DReplace = Convert.ToInt16(txtTDReplace.Text);
            selectedAsset.Maintenance = (TimberMaintenance)Enum.Parse(typeof(TimberMaintenance), ddlTMaintenance.SelectedItem.Text);
            selectedAsset.Contact = (TimberContact)Enum.Parse(typeof(TimberContact), ddlTContact.SelectedItem.Text);
            selectedAsset.Knot = (TimberKnot)Enum.Parse(typeof(TimberKnot), ddlTKnot.SelectedItem.Text);
            selectedAsset.Zone = (TimberZone)Enum.Parse(typeof(TimberZone), ddlTZone.SelectedItem.Text);
            selectedAsset.Exposure = (TimberExposure)Enum.Parse(typeof(TimberExposure), ddlTExposure.SelectedItem.Text);

            timberAssets.Add(selectedAsset);
            timberTemplate.TimberAssetList = timberAssets;
            CurrentTimberTemplate = timberTemplate;

            txtTAssetCode.ReadOnly = false;
            btnAddTimberAsset.Text = Common.EVENT_NAME_SAVE;
            ClearTimberAsset();
            LoadTimberAssets();
            HideDetailTimber = true;
        }

        /// <summary>
        /// This method is used to clear Timber asset details from the footer input controls
        /// </summary>
        private void ClearTimberAsset()
        {
            if (String.Equals(btnAddTimberAsset.Text, Common.EVENT_NAME_UPDATE))
            {
                LoadTimberAssets();
                HideDetailTimber = false;
            }

            txtTAssetCode.Text = String.Empty;
            txtTYearInstalled.Text = String.Empty;
            txtTDescription.Text = String.Empty;
            txtTType.Text = String.Empty;
            ddlTHW.SelectedValue = Common.ZERO_VALUE;
            ddlTSW.SelectedValue = Common.ZERO_VALUE;
            txtTDo.Text = String.Empty;
            txtTDSap.Text = String.Empty;
            txtTDReplace.Text = String.Empty;
            ddlTMaintenance.SelectedValue = Common.ZERO_VALUE;
            ddlTContact.SelectedValue = Common.ZERO_VALUE;
            ddlTKnot.SelectedValue = Common.ZERO_VALUE;
            ddlTZone.SelectedValue = Common.ZERO_VALUE;
            ddlTExposure.SelectedValue = Common.ZERO_VALUE;
            btnAddTimberAsset.Text = Common.EVENT_NAME_SAVE;
        }

        #endregion

        #region Coefficients Cell Updates

        /// <summary>
        /// This method is used to update the Timber excel sheet Asset details with the new values entered
        /// </summary>
        /// <param name="allSheets"></param>
        /// <param name="methods"></param>
        private void UpdateTimberExcelSheet(ref Excel.Sheets allSheets, ref CommonMethods methods)
        {
            Excel.Worksheet coefficientsSheet = (Excel.Worksheet)allSheets.get_Item(Common.WORK_SHEET_COEFFICIENTS);
            TimberTemplate template = UserSelection.TimberTemplate;

            UpdateTimberTable1(template, ref coefficientsSheet);
            UpdateTimberTable2(template, ref coefficientsSheet);
            UpdateTimberTable3(template, ref coefficientsSheet);
            UpdateTimberTable4(template, ref coefficientsSheet);
            UpdateTimberTable5(template, ref coefficientsSheet);
            UpdateTimberTable6(template, ref coefficientsSheet);
        }

        private void UpdateTimberTable1(TimberTemplate template, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isTable1Update)
            {
                // Hearwood
                coefficientSheet.Cells[4, "H"] = template.valTbl1HWZAC1;
                coefficientSheet.Cells[4, "I"] = template.valTbl1HWZDG1;
                coefficientSheet.Cells[5, "H"] = template.valTbl1HWZAC2;
                coefficientSheet.Cells[5, "I"] = template.valTbl1HWZDG2;
                coefficientSheet.Cells[6, "H"] = template.valTbl1HWZAC3;
                coefficientSheet.Cells[6, "I"] = template.valTbl1HWZDG3;
                coefficientSheet.Cells[7, "H"] = template.valTbl1HWZAC4;
                coefficientSheet.Cells[7, "I"] = template.valTbl1HWZDG4;
                // Sapwood of softwood
                coefficientSheet.Cells[8, "H"] = template.valTbl1SPSUZAC;
                coefficientSheet.Cells[8, "I"] = template.valTbl1SPSUZDG;
                coefficientSheet.Cells[9, "H"] = template.valTbl1SPSCRZAC1;
                coefficientSheet.Cells[9, "I"] = template.valTbl1SPSCRZDG1;
                coefficientSheet.Cells[10, "H"] = template.valTbl1SPSCRZAC2;
                coefficientSheet.Cells[10, "I"] = template.valTbl1SPSCRZDG2;
                coefficientSheet.Cells[11, "H"] = template.valTbl1SPSCCZAC1;
                coefficientSheet.Cells[11, "I"] = template.valTbl1SPSCCZDG1;
                coefficientSheet.Cells[12, "H"] = template.valTbl1SPSCCZAC2;
                coefficientSheet.Cells[12, "I"] = template.valTbl1SPSCCZDG2;
                coefficientSheet.Cells[13, "H"] = template.valTbl1SPSCCZAC3;
                coefficientSheet.Cells[13, "I"] = template.valTbl1SPSCCZDG3;
                coefficientSheet.Cells[14, "H"] = template.valTbl1SPSCCZAC4;
                coefficientSheet.Cells[14, "I"] = template.valTbl1SPSCCZDG4;
                coefficientSheet.Cells[15, "H"] = template.valTbl1SPSDBTZAC;
                coefficientSheet.Cells[15, "I"] = template.valTbl1SPSDBTZDG;
                // Sapwood of hardwood
                coefficientSheet.Cells[16, "H"] = template.valTbl1SPHUZAC;
                coefficientSheet.Cells[16, "I"] = template.valTbl1SPHUZDG;
                coefficientSheet.Cells[17, "H"] = template.valTbl1SPHCRZAC1;
                coefficientSheet.Cells[17, "I"] = template.valTbl1SPHCRZDG1;
                coefficientSheet.Cells[18, "H"] = template.valTbl1SPHCRZAC2;
                coefficientSheet.Cells[18, "I"] = template.valTbl1SPHCRZDG2;
                coefficientSheet.Cells[19, "H"] = template.valTbl1SPHCCZAC1;
                coefficientSheet.Cells[19, "I"] = template.valTbl1SPHCCZDG1;
                coefficientSheet.Cells[20, "H"] = template.valTbl1SPHCCZAC2;
                coefficientSheet.Cells[20, "I"] = template.valTbl1SPHCCZDG2;
                coefficientSheet.Cells[21, "H"] = template.valTbl1SPHCCZAC3;
                coefficientSheet.Cells[21, "I"] = template.valTbl1SPHCCZDG3;
                coefficientSheet.Cells[22, "H"] = template.valTbl1SPHDBTZAC;
                coefficientSheet.Cells[22, "I"] = template.valTbl1SPHDBTZDG;
            }
        }

        private void UpdateTimberTable2(TimberTemplate template, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isTable2Update)
            {
                coefficientSheet.Cells[4, "M"] = template.valTbl2Class1ZAD;
                coefficientSheet.Cells[4, "N"] = template.valTbl2Class1ZEG;
                coefficientSheet.Cells[5, "M"] = template.valTbl2Class2ZAD;
                coefficientSheet.Cells[5, "N"] = template.valTbl2Class2ZEG;
                coefficientSheet.Cells[6, "M"] = template.valTbl2Class3ZAD;
                coefficientSheet.Cells[6, "N"] = template.valTbl2Class3ZEG;
            }
        }

        private void UpdateTimberTable3(TimberTemplate template, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isTable3Update)
            {
                coefficientSheet.Cells[3, "X"] = template.valTbl3Exp1;
                coefficientSheet.Cells[4, "X"] = template.valTbl3Exp2;
            }
        }

        private void UpdateTimberTable4(TimberTemplate template, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isTable4Update)
            {
                coefficientSheet.Cells[7, "X"] = template.valTbl4Mtn1;
                coefficientSheet.Cells[8, "X"] = template.valTbl4Mtn2;
            }
        }

        private void UpdateTimberTable5(TimberTemplate template, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isTable5Update)
            {
                coefficientSheet.Cells[11, "X"] = template.valTbl5Cnt1;
                coefficientSheet.Cells[12, "X"] = template.valTbl5Cnt2;
            }
        }

        private void UpdateTimberTable6(TimberTemplate template, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isTable6Update)
            {
                coefficientSheet.Cells[15, "X"] = template.valTbl6Knt1;
                coefficientSheet.Cells[16, "X"] = template.valTbl6Knt2;
                coefficientSheet.Cells[17, "X"] = template.valTbl6Knt3;
            }
        }

        #endregion

        #region Input Data Upload / Save

        /// <summary>
        /// This method is used to update the excel cells according to the Input data upload sheet
        /// </summary>
        /// <param name="allSheets"></param>
        /// <param name="methods"></param>
        private void UpdateTimberInputData(ref Excel.Sheets allSheets, ref CommonMethods methods)
        {
            Excel.Worksheet inputDataSheet = (Excel.Worksheet)allSheets.get_Item(Common.WORK_SHEET_INPUT_DATA);

            TimberTemplate template = UserSelection.TimberTemplate;
            int assetStartRowIndex = Common.ASSET_START_ROW_INDEX;

            foreach (TimberAsset asset in template.TimberAssetList)
            {
                if (asset.IsEdited)
                {
                    inputDataSheet.Cells[assetStartRowIndex, "A"] = asset.AssetCode;
                    inputDataSheet.Cells[assetStartRowIndex, "B"] = asset.YearInstalled;
                    inputDataSheet.Cells[assetStartRowIndex, "C"] = asset.Description;
                    inputDataSheet.Cells[assetStartRowIndex, "D"] = asset.Type;
                    inputDataSheet.Cells[assetStartRowIndex, "E"] = Enum.GetName(typeof(TimberHW), asset.HW);
                    inputDataSheet.Cells[assetStartRowIndex, "F"] = Enum.GetName(typeof(TimberSW), asset.SW);
                    inputDataSheet.Cells[assetStartRowIndex, "G"] = asset.Do;
                    inputDataSheet.Cells[assetStartRowIndex, "H"] = asset.DSap;
                    inputDataSheet.Cells[assetStartRowIndex, "I"] = asset.DReplace;
                    inputDataSheet.Cells[assetStartRowIndex, "J"] = Enum.GetName(typeof(TimberMaintenance), asset.Maintenance);
                    inputDataSheet.Cells[assetStartRowIndex, "K"] = Enum.GetName(typeof(TimberContact), asset.Contact);
                    inputDataSheet.Cells[assetStartRowIndex, "L"] = Enum.GetName(typeof(TimberKnot), asset.Knot);
                    inputDataSheet.Cells[assetStartRowIndex, "M"] = Enum.GetName(typeof(TimberZone), asset.Zone);
                    inputDataSheet.Cells[assetStartRowIndex, "N"] = Enum.GetName(typeof(TimberExposure), asset.Exposure);
                }

                assetStartRowIndex++;
            }
        }

        /// <summary>
        /// This method is used to load the input data according to the upload excel sheet
        /// </summary>
        /// <param name="range"></param>
        /// <returns></returns>
        private IList<TimberAsset> LoadTimberInputData(Excel.Range range)
        {
            IList<TimberAsset> timberAssets = new List<TimberAsset>();

            int rowIndex = Common.TIMBER_UPLOAD_ASSET_START_ROW_INDEX;
            string assetCode = String.Empty;
            string description = String.Empty;
            IList<TimberAsset> timberAssetList = new List<TimberAsset>();
            bool isErrorExists = false;

            do
            {
                assetCode = ValidateAssetCode((range.Cells[rowIndex, 1] as Excel.Range).Value2, rowIndex, ref isErrorExists);
                string yearInstalledStr = Convert.ToString((range.Cells[rowIndex, 2] as Excel.Range).Value2);
                description = Convert.ToString((range.Cells[rowIndex, 3] as Excel.Range).Value2);

                if (!String.IsNullOrEmpty(assetCode) & !String.IsNullOrEmpty(yearInstalledStr) & !String.IsNullOrEmpty(description))
                {
                    int yearInstalled = ValidateYearbuilt(yearInstalledStr, rowIndex, ref isErrorExists);
                    string type = Convert.ToString((range.Cells[rowIndex, 4] as Excel.Range).Value2);
                    string hw = Convert.ToString((range.Cells[rowIndex, 5] as Excel.Range).Value2);
                    string sw = Convert.ToString((range.Cells[rowIndex, 6] as Excel.Range).Value2);
                    int doValue = ValidateTDo((range.Cells[rowIndex, 7] as Excel.Range).Value2, rowIndex, ref isErrorExists);
                    int dsap = ValidateTDSap((range.Cells[rowIndex, 8] as Excel.Range).Value2, rowIndex, ref isErrorExists);
                    int dreplace = ValidateTDReplace((range.Cells[rowIndex, 9] as Excel.Range).Value2, rowIndex, ref isErrorExists);
                    string maintenance = Convert.ToString((range.Cells[rowIndex, 10] as Excel.Range).Value2);
                    string contact = Convert.ToString((range.Cells[rowIndex, 11] as Excel.Range).Value2);
                    string knot = Convert.ToString((range.Cells[rowIndex, 12] as Excel.Range).Value2);
                    string zone = Convert.ToString((range.Cells[rowIndex, 13] as Excel.Range).Value2);
                    string exposure = Convert.ToString((range.Cells[rowIndex, 14] as Excel.Range).Value2);

                    if (!isErrorExists)
                    {
                        TimberAsset asset = CommonMethods.CreateTimberAsset(assetCode, yearInstalled, description,
                            type, hw, sw, doValue, dsap, dreplace, maintenance, contact, knot, zone, exposure, true);

                        timberAssetList.Add(asset);
                    }
                }

                rowIndex++;
            }
            while (!IsExcelRowEmptty(assetCode, description, rowIndex));

            return timberAssetList;
        }

        #endregion

        #region Excel : Input Data Validations

        private int ValidateTDo(object excelField, int rowNo, ref bool isErrorExists)
        {
            Int16 doValue = 0;
            string doValueStr = Convert.ToString(excelField);
            Int16.TryParse(doValueStr, out doValue);

            if (!String.Equals(doValueStr, Common.ZERO_VALUE))
            {
                if (doValue == 0)
                {
                    invalidTDoList.Add(rowNo);
                    isErrorExists = true;
                }
            }

            return doValue;
        }

        private int ValidateTDSap(object excelField, int rowNo, ref bool isErrorExists)
        {
            Int16 dsap = 0;
            string dsapStr = Convert.ToString(excelField);
            Int16.TryParse(dsapStr, out dsap);

            if (!String.Equals(dsapStr, Common.ZERO_VALUE))
            {
                if (dsap == 0)
                {
                    invalidTDSapList.Add(rowNo);
                    isErrorExists = true;
                }
            }

            return dsap;
        }

        private int ValidateTDReplace(object excelField, int rowNo, ref bool isErrorExists)
        {
            Int16 dreplace = 0;
            string dreplaceStr = Convert.ToString(excelField);
            Int16.TryParse(dreplaceStr, out dreplace);

            if (!String.Equals(dreplaceStr, Common.ZERO_VALUE))
            {
                if (dreplace == 0)
                {
                    invalidTDReplaceList.Add(rowNo);
                    isErrorExists = true;
                }
            }

            return dreplace;
        }

        #endregion

        #endregion

        #region Private Steel Methods

        #region Common

        /// <summary>
        /// This method is used to retrieve the Steel asset list from session and bind to the list
        /// </summary>
        private void LoadSteelAssets()
        {
            if (CurrentSteelTemplate.SteelAssetList != null)
            {
                lstAssetsSteel.DataSource = CurrentSteelTemplate.SteelAssetList;
                lstAssetsSteel.DataBind();
            }

            divGridSteel.Style["display"] = CurrentSteelTemplate.SteelAssetList != null && CurrentSteelTemplate.SteelAssetList.Count > 0 ? String.Empty : "none";
            btnNext.Visible = CurrentSteelTemplate.SteelAssetList != null && CurrentSteelTemplate.SteelAssetList.Count > 0;
            HideDetailSteel = CurrentSteelTemplate.SteelAssetList != null && CurrentSteelTemplate.SteelAssetList.Count > 0;
        }

        /// <summary>
        /// This method is used to check wether a Steel asset exists with a given Asset Code
        /// </summary>
        /// <param name="assetList"></param>
        /// <param name="assetCode"></param>
        /// <returns></returns>
        private bool IsAssetAlreadyExists(IList<SteelAsset> assetList, string assetCode)
        {
            if (assetList != null)
            {
                foreach (SteelAsset asset in assetList)
                {
                    if (String.Equals(asset.AssetCode, assetCode))
                        return true;
                }
            }

            return false;
        }

        /// <summary>
        /// This method is used to retrieve the particular Steel asset from the session by the AssetCode
        /// </summary>
        /// <param name="assetCode"></param>
        /// <param name="assetList"></param>
        /// <returns></returns>
        private SteelAsset GetSteelAssetByCode(string assetCode, IList<SteelAsset> assetList)
        {
            SteelAsset selectedAsset = new SteelAsset();

            foreach (SteelAsset asset in assetList)
            {
                if (String.Equals(asset.AssetCode, assetCode))
                {
                    selectedAsset = asset;
                }
            }

            return selectedAsset;
        }

        /// <summary>
        /// This method is used to retrieve the values from input controls and create a new 
        /// Steel asset using them and add it to the session
        /// </summary>
        private void AddSteelAsset()
        {
            SteelTemplate SteelTemplate = CurrentSteelTemplate;
            IList<SteelAsset> SteelAssets = SteelTemplate.SteelAssetList;

            if (SteelAssets == null)
                SteelAssets = new List<SteelAsset>();

            string assetCode = txtSAssetCode.Text;

            if (!IsAssetAlreadyExists(SteelAssets, assetCode))
            {
                int yearBuilt = Convert.ToInt16(txtSYearBuilt.Text);
                string description = txtSDescription.Text;
                string zone = ddlSZone.SelectedItem.Text;
                string hazardZone = ddlSHazardZone.Text;
                string coastalZone = ddlSCoastalZone.Text;
                string coastalClass = ddlSCoastalExposure.Text;
                string siteClass = ddlSSiteClassification.Text;
                decimal distFromCost = Convert.ToDecimal(txtSDistFromCost.Text);

                SteelAsset asset = CommonMethods.CreateSteelAsset(assetCode, yearBuilt, description, 
                    zone, hazardZone, coastalZone, coastalClass, siteClass, distFromCost, true);
                SteelAssets.Add(asset);

                SteelTemplate.SteelAssetList = SteelAssets;
                CurrentSteelTemplate = SteelTemplate;
                ClearSteelAsset();
                LoadSteelAssets();
            }
            else
            {
                string alert = "<script type=\"text/javascript\">alert(\"Asset Code already exists. <br /> Please try a different Asset Code.\")</script>";
                ClientScript.RegisterStartupScript(typeof(Page), "AssetDuplication", alert);
            }
        }

        /// <summary>
        /// This method is used to update the particular Steel asset with values entered to the input controls
        /// </summary>
        private void UpdateSteelAsset()
        {
            SteelTemplate SteelTemplate = CurrentSteelTemplate;
            IList<SteelAsset> SteelAssets = SteelTemplate.SteelAssetList;

            string assetCode = txtSAssetCode.Text;
            SteelAsset selectedAsset = GetSteelAssetByCode(assetCode, SteelAssets);
            SteelAssets.Remove(selectedAsset);

            selectedAsset.YearBuilt = Convert.ToInt16(txtSYearBuilt.Text);
            selectedAsset.Description = txtSDescription.Text;
            selectedAsset.Zone = (Zone)Enum.Parse(typeof(Zone), ddlSZone.SelectedItem.Text);
            selectedAsset.HazardZone = (SteelHazardZone)Enum.Parse(typeof(SteelHazardZone), ddlSHazardZone.SelectedItem.Text);
            selectedAsset.CoastalZone = (SteelCoastalZone)Enum.Parse(typeof(SteelCoastalZone), ddlSCoastalZone.SelectedItem.Text);
            selectedAsset.CoastClass = (SteelCoastalClass)Enum.Parse(typeof(SteelCoastalClass), ddlSCoastalExposure.SelectedItem.Text);
            selectedAsset.SiteClass = (SteelSiteClassification)Enum.Parse(typeof(SteelSiteClassification), ddlSSiteClassification.SelectedItem.Text);
            selectedAsset.DistFromCost = Convert.ToDecimal(txtSDistFromCost.Text);

            SteelAssets.Add(selectedAsset);
            SteelTemplate.SteelAssetList = SteelAssets;
            CurrentSteelTemplate = SteelTemplate;

            txtSAssetCode.ReadOnly = false;
            btnAddSteelAsset.Text = Common.EVENT_NAME_SAVE;
            ClearSteelAsset();
            LoadSteelAssets();
            HideDetailSteel = true;
        }

        /// <summary>
        /// This method is used to clear Steel asset details from the footer input controls
        /// </summary>
        private void ClearSteelAsset()
        {
            if (String.Equals(btnAddSteelAsset.Text, Common.EVENT_NAME_UPDATE))
            {
                LoadSteelAssets();
                HideDetailSteel = false;
            }

            txtSAssetCode.Text = String.Empty;
            txtSYearBuilt.Text = String.Empty;
            txtSDescription.Text = String.Empty;
            ddlSZone.SelectedValue = Common.ZERO_VALUE;
            ddlSHazardZone.SelectedValue = Common.ZERO_VALUE;
            ddlSCoastalZone.SelectedValue = Common.ZERO_VALUE;
            ddlSCoastalExposure.SelectedValue = Common.ZERO_VALUE;
            ddlSSiteClassification.SelectedValue = Common.ZERO_VALUE;
            txtSDistFromCost.Text = String.Empty;

            btnAddSteelAsset.Text = Common.EVENT_NAME_SAVE;
        }

        #endregion

        #region Coefficients Cell Updates

        /// <summary>
        /// This method is used to update the Steel excel sheet Asset details with the new values entered
        /// </summary>
        /// <param name="allSheets"></param>
        /// <param name="methods"></param>
        private void UpdateSteelExcelSheet(ref Excel.Sheets allSheets, ref CommonMethods methods)
        {
            Excel.Worksheet coefficientsSheet = (Excel.Worksheet)allSheets.get_Item(Common.WORK_SHEET_COEFFICIENTS);
            SteelTemplate template = UserSelection.SteelTemplate;

            UpdateNumberOfRainDays(template, methods, ref coefficientsSheet);
            UpdateZoneFactors(template, methods, ref coefficientsSheet);
            UpdateCoastalExposure(template, methods, ref coefficientsSheet);
            UpdateSiteClassification(template, methods, ref coefficientsSheet);
        }

        private void UpdateNumberOfRainDays(SteelTemplate template, CommonMethods methods, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isNoOfRDUpdated)
            {
                coefficientSheet.Cells[3, "L"] = template.NORDA;
                coefficientSheet.Cells[4, "L"] = template.NORDB;
                coefficientSheet.Cells[5, "L"] = template.NORDC;
                coefficientSheet.Cells[6, "L"] = template.NORDD;
                coefficientSheet.Cells[7, "L"] = template.NORDE;
            }
        }

        private void UpdateZoneFactors(SteelTemplate template, CommonMethods methods, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isZoneFactorUpdated)
            {
                coefficientSheet.Cells[3, "O"] = template.ZFAC1;
                coefficientSheet.Cells[3, "P"] = template.ZFAO1;
                coefficientSheet.Cells[4, "O"] = template.ZFAC2;
                coefficientSheet.Cells[4, "P"] = template.ZFAO2;
                coefficientSheet.Cells[5, "O"] = template.ZFAC3;
                coefficientSheet.Cells[5, "P"] = template.ZFAO3;
            }
        }

        private void UpdateCoastalExposure(SteelTemplate template, CommonMethods methods, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isCoastalExposureUpdated)
            {
                coefficientSheet.Cells[3, "U"] = template.CEBCExp1;
                coefficientSheet.Cells[4, "U"] = template.CEBCExp2;
                coefficientSheet.Cells[5, "U"] = template.CEBCExp3;
                coefficientSheet.Cells[6, "U"] = template.CEBCExp4;
            }
        }

        private void UpdateSiteClassification(SteelTemplate template, CommonMethods methods, ref Excel.Worksheet coefficientSheet)
        {
            if (template.isSiteClassificationUpdated)
            {
                coefficientSheet.Cells[10, "U"] = template.SCSite1;
                coefficientSheet.Cells[11, "U"] = template.SCSite2;
                coefficientSheet.Cells[12, "U"] = template.SCSite3;
                coefficientSheet.Cells[13, "U"] = template.SCSite4;
            }
        }
        
        #endregion

        #region Input Data Upload / Save

        /// <summary>
        /// This method is used to update the excel cells according to the Input data upload sheet
        /// </summary>
        /// <param name="allSheets"></param>
        /// <param name="methods"></param>
        private void UpdateSteelInputData(ref Excel.Sheets allSheets, ref CommonMethods methods)
        {
            Excel.Worksheet inputDataSheet = (Excel.Worksheet)allSheets.get_Item(Common.WORK_SHEET_INPUT_DATA);

            SteelTemplate template = UserSelection.SteelTemplate;
            int assetStartRowIndex = Common.ASSET_START_ROW_INDEX;

            foreach (SteelAsset asset in template.SteelAssetList)
            {
                if (asset.IsEdited)
                {
                    inputDataSheet.Cells[assetStartRowIndex, "A"] = asset.AssetCode;
                    inputDataSheet.Cells[assetStartRowIndex, "B"] = asset.YearBuilt;
                    inputDataSheet.Cells[assetStartRowIndex, "C"] = asset.Description;
                    inputDataSheet.Cells[assetStartRowIndex, "D"] = Enum.GetName(typeof(Zone), asset.Zone);
                    inputDataSheet.Cells[assetStartRowIndex, "E"] = Enum.GetName(typeof(SteelHazardZone), asset.HazardZone);
                    inputDataSheet.Cells[assetStartRowIndex, "F"] = Enum.GetName(typeof(SteelCoastalZone), asset.CoastalZone);
                    inputDataSheet.Cells[assetStartRowIndex, "G"] = Enum.GetName(typeof(SteelCoastalClass), asset.CoastClass);
                    inputDataSheet.Cells[assetStartRowIndex, "H"] = Enum.GetName(typeof(SteelSiteClassification), asset.SiteClass);
                    inputDataSheet.Cells[assetStartRowIndex, "I"] = asset.DistFromCost;
                }

                assetStartRowIndex++;
            }
        }

        /// <summary>
        /// This method is used to load the input data according to the upload excel sheet
        /// </summary>
        /// <param name="range"></param>
        /// <returns></returns>
        private IList<SteelAsset> LoadSteelInputData(Excel.Range range)
        {
            IList<SteelAsset> SteelAssets = new List<SteelAsset>();

            int rowIndex = Common.STEEL_UPLOAD_ASSET_START_ROW_INDEX;
            string assetCode = String.Empty;
            string description = String.Empty;
            IList<SteelAsset> SteelAssetList = new List<SteelAsset>();
            bool isErrorExists = false;

            do
            {
                assetCode = ValidateAssetCode((range.Cells[rowIndex, 1] as Excel.Range).Value2, rowIndex, ref isErrorExists);
                string yearBuiltStr = Convert.ToString((range.Cells[rowIndex, 2] as Excel.Range).Value2);
                description = Convert.ToString((range.Cells[rowIndex, 3] as Excel.Range).Value2);

                if (!String.IsNullOrEmpty(assetCode) & !String.IsNullOrEmpty(yearBuiltStr) & !String.IsNullOrEmpty(description))
                {
                    int yearBuilt = ValidateYearbuilt(yearBuiltStr, rowIndex, ref isErrorExists);
                    string zone = Convert.ToString((range.Cells[rowIndex, 4] as Excel.Range).Value2);
                    string hazardZone = Convert.ToString((range.Cells[rowIndex, 5] as Excel.Range).Value2);
                    string coastalZone = Convert.ToString((range.Cells[rowIndex, 6] as Excel.Range).Value2);
                    string coastalClass = Convert.ToString((range.Cells[rowIndex, 7] as Excel.Range).Value2);
                    string siteClass = Convert.ToString((range.Cells[rowIndex, 8] as Excel.Range).Value2);
                    decimal distFromCost = ValidateDistFromCost((range.Cells[rowIndex, 9] as Excel.Range).Value2, rowIndex, ref isErrorExists);

                    if (!isErrorExists)
                    {
                        SteelAsset asset = CommonMethods.CreateSteelAsset(assetCode, yearBuilt, description, 
                            zone, hazardZone, coastalZone, coastalClass, siteClass, distFromCost, true);

                        SteelAssetList.Add(asset);
                    }
                }

                rowIndex++;
            }
            while (!IsExcelRowEmptty(assetCode, description, rowIndex));

            return SteelAssetList;
        }

        #endregion

        #endregion

        #endregion

        #region Protected Members

        protected string _Extentions = "xls, xlsx";

        protected string GetExtentions()
        {
            var Exts = _Extentions.Split(',');
            _Extentions = String.Empty;
            string Result = String.Empty;

            foreach (string str in Exts)
            {
                Result = String.IsNullOrEmpty(Result) ? String.Format("Extentions.push(\"{0}\");", str.Trim().Replace(".", String.Empty)) : String.Format("{0}\nExtentions.push(\"{1}\");", Result, str.Trim().Replace(".", String.Empty));
                _Extentions = String.IsNullOrEmpty(_Extentions) ? String.Format("{0}", str.ToUpper()) : String.Format("{0}, {1}", _Extentions, str.ToUpper());
            }
            return Result;
        }

        #endregion

    }
}
