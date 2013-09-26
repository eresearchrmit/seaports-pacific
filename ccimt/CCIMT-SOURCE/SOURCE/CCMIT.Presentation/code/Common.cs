using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CCIMT.Presentation
{
    #region Structs

    public class CurrentSelection
    {
        public TemplateType SelectedTemplateType { get; set; }
        public Location SelectedLocation { get; set; }
        public CarbonEmissionScenario SelectedCES { get; set; }
        public FutureClimateModel SelectedFCM { get; set; }
        public int SelectedYear { get; set; }

        public ConcreteTemplate ConcreteTemplate { get; set; }
        public TimberTemplate TimberTemplate { get; set; }
        public SteelTemplate SteelTemplate { get; set; }
    }

    #endregion
        
    public class Common
    {

        #region Constant Variables
        
        public const string PAGE_LOGIN_PAGE = "default.aspx";

        public const string TEMPLATE_PATH = "~/content/templates/";
        public const string OBJECT_PATH = "~/content/objects/";
        public const string INPUTDATA_PATH = "~/content/inputdata/";

        public const string TEMPLATE_CONCRETE_GLADSTONE = "Concrete_gladstone_v7.6.1.xlsm";
        public const string TEMPLATE_TIMBER_GLADSTONE = "Timber_gladstone_v6.3.xlsm";
        public const string TEMPLATE_STEEL_GLADSTONE = "Steel_gladstone_v1.xlsm";
        public const string TEMPLATE_CONCRETE_PORTKEMBLA = "Concrete_kembla_v7.6.1.xlsm";
        public const string TEMPLATE_TIMBER_PORTKEMBLA = "Timber_kembla_v6.3.xlsm";
        public const string TEMPLATE_STEEL_PORTKEMBLA = "Steel_kembla_v1.xlsm";
        public const string TEMPLATE_CONCRETE_SYDNEY = "Concrete_sydney_v7.6.1.xlsm";
        public const string TEMPLATE_TIMBER_SYDNEY = "Timber_sydney_v6.3.xlsm";
        public const string TEMPLATE_STEEL_SYDNEY = "Steel_sydney_v1.xlsm";

        public const string WORK_SHEET_MASTERSHEET = "Mastersheet";
        public const string WORK_SHEET_COEFFICIENTS = "Coefficients";
        public const string WORK_SHEET_INPUT_DATA = "InputData";

        public const string MACRO_NAME_COPY_SHEETS = "Copy_Sheets_Rename";

        public const string EVENT_NAME_EDIT = "Edit";
        public const string EVENT_NAME_SAVE = "Save";
        public const string EVENT_NAME_CANCEL = "Cancel";
        public const string EVENT_NAME_UPDATE = "Update";

        public const int ASSET_START_ROW_INDEX = 5;

        public const int CONCRETE_UPLOAD_ASSET_START_ROW_INDEX = 2;
        public const int TIMBER_UPLOAD_ASSET_START_ROW_INDEX = 3;
        public const int STEEL_UPLOAD_ASSET_START_ROW_INDEX = 2;

        // Concrete Cell Starters
        public const int CONCRETE_ROW_NO_BASE = 26;
        public const int CONCRETE_ROW_NO_A1F1_ML = 98;
        public const int CONCRETE_ROW_NO_A1F1_HD = 170;
        public const int CONCRETE_ROW_NO_A1F1_CW = 242;
        public const int CONCRETE_ROW_NO_A1B_ML = 314;
        public const int CONCRETE_ROW_NO_A1B_HD = 386;
        public const int CONCRETE_ROW_NO_A1B_CW = 458;

        // Timber Cell Starters
        public const int TIMBER_ROW_NO_BASE = 10;
        public const int TIMBER_ROW_NO_A1F1_ML = 82;
        public const int TIMBER_ROW_NO_A1F1_HD = 154;        
        public const int TIMBER_ROW_NO_A1F1_CW = 226;
        public const int TIMBER_ROW_NO_A1B_ML = 298;
        public const int TIMBER_ROW_NO_A1B_HD = 370;        
        public const int TIMBER_ROW_NO_A1B_CW = 442;

        // Steel Cell Starters
        public const int STEEL_ROW_NO_BASE = 26;
        public const int STEEL_ROW_NO_A1F1_ML = 98;
        public const int STEEL_ROW_NO_A1F1_HD = 170;
        public const int STEEL_ROW_NO_A1F1_CW = 242;
        public const int STEEL_ROW_NO_A1B_ML = 314;
        public const int STEEL_ROW_NO_A1B_HD = 386;
        public const int STEEL_ROW_NO_A1B_CW = 458;

        public const string TITLE_BASE = "Base (Reference)";

        public const string TITLE_A1F1_MRI = "A1FI MRI-CGCM2.3.2";
        public const string TITLE_A1F1_CSIRO = "A1FI CSIRO Mk3.5";
        public const string TITLE_A1F1_MIROC = "A1FI MIROC-Medres";
        public const string TITLE_A1B_MRI = "A1B MRI-CGCM2.3.2";
        public const string TITLE_A1B_CSIRO = "A1B CSIRO Mk3.5";
        public const string TITLE_A1B_MIROC = "A1B MIROC-Medres";
        
        public const int GRAPH_X_MIN = 2000;
        public const int GRAPH_X_MAX = 2070;
        public const int GRAPH_X_INTERVAL = 1;

        public const string ZERO_VALUE = "0";
        public const string LINE_BREAK = "<br />";
        public const string ERROR = "ERROR";
        
        #endregion        
        
    }
}