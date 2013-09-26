//Common
function SetMinHieght(Element) 
{
    var Height = Element.clientHeight < window.innerHeight ? window.innerHeight : Element.clientHeight;
    //var NewHeight = (Height < window.screen.height ? window.screen.height : Height);
    Element.style.minHeight = Height + "px";
    Element.clientHeight = Height;
}

function Redirect(Url)
{
	location.href = Url;
}

//Default Page
function HomeNext_Click()
{
	var Result = false;
	var ValLocation = document.getElementById('lblValLocation');
	var ValModal = document.getElementById('lblValModel');
	var ValScenario = document.getElementById('lblValScenario');
    var ValYear = document.getElementById('lblValYear');
	
	var DrpLocation = document.getElementById('drpLocation');
	var DrpScenario = document.getElementById('drpScenario');
	var DrpModel = document.getElementById('drpModel');
    var DrpYear = document.getElementById('drpYear');	
	
	if(DrpLocation.selectedIndex != 0 && DrpScenario.selectedIndex != 0 && DrpModel.selectedIndex != 0 && DrpYear.selectedIndex != 0)
		Result = true;

	ValLocation.style.display = DrpLocation.selectedIndex == 0 ? "" : "none";
	ValScenario.style.display = DrpScenario.selectedIndex == 0 ? "" : "none";
	ValModal.style.display = DrpModel.selectedIndex == 0 ? "" : "none";
	ValYear.style.display = DrpYear.selectedIndex == 0 ? "" : "none";

	if (Result)
	    document.getElementById("cmdNext").click();
}

function Page2Next_Click(mat)
{
	var DrpConstMat = document.getElementById('drpMaterial');
	
	if(DrpConstMat.selectedIndex != 0)
		Result = true;
	
	var location = GetQuerystring('loaction', null);
	var scenario = GetQuerystring('scenario', null);;
	var model = GetQuerystring('model', null);;
	
	if (Result) 
		Redirect('Page3.html?mat=' + mat + '&loaction=' + location  + '&scenario=' + scenario + '&model=' + model);
}

function Page3Next_Click()
{
	var mat = GetQuerystring('mat', null);
	var location = GetQuerystring('loaction', null);
	var scenario = GetQuerystring('scenario', null);;
	var model = GetQuerystring('model', null);;
	
	Redirect('Page4.html?mat=' + mat + '&loaction=' + location  + '&scenario=' + scenario + '&model=' + model);
}

function Page3ToPage1_Click()
{
	var location = GetQuerystring('loaction', null);
	var scenario = GetQuerystring('scenario', null);;
	var model = GetQuerystring('model', null);;
	
	Redirect('default.html?loaction=' + location  + '&scenario=' + scenario + '&model=' + model);
}

function Page4n3n2ToPage1_Click()
{
	var location = GetQuerystring('loaction', null);
	var scenario = GetQuerystring('scenario', null);;
	var model = GetQuerystring('model', null);;
	
	Redirect('default.html?loaction=' + location  + '&scenario=' + scenario + '&model=' + model);
}

function Page4n3ToPage2_Click()
{
	var mat = GetQuerystring('mat', null);
	var location = GetQuerystring('loaction', null);
	var scenario = GetQuerystring('scenario', null);;
	var model = GetQuerystring('model', null);;
	
	Redirect('Page2.html?mat=' + mat + '&loaction=' + location  + '&scenario=' + scenario + '&model=' + model);
}

function Page4ToPage3_Click()
{
	var mat = GetQuerystring('mat', null);
	var location = GetQuerystring('loaction', null);
	var scenario = GetQuerystring('scenario', null);;
	var model = GetQuerystring('model', null);;
	
	Redirect('Page3.html?mat=' + mat + '&loaction=' + location  + '&scenario=' + scenario + '&model=' + model);
}

//Material Page
function ValidateMaterial()
{
	var Result = false;
	var ValMaterial = document.getElementById('lblMaterial');
    var DrpMaterial = document.getElementById('drpMaterial');

    if (DrpMaterial.selectedIndex != 0)
	{
		Result = true;
		ValMaterial.style.display = "none";
	}
	else
		ValMaterial.style.display = "";	
	
	return Result;
}

function SwitchView(Dropdown)
{
	var divViewTimber = document.getElementById("divViewTimber");
	var divViewConcrete = document.getElementById("divViewConcrete");
	
	divViewTimber.style.display = "none";
	divViewConcrete.style.display = "none";

	switch(Dropdown.selectedIndex)
	{
	    case 1:
	        divViewConcrete.style.display = "";
	        break;
		
		case 2:
		    alert("Sorry. Data cannot be found for the requested criteria.");
		    break;		

        default:
            divViewTimber.style.display = "";
            break;
	}
}

function Edit_Click(OpButtonId, CancelButtonId)
{
	var OpButton = document.getElementById(OpButtonId);
	var CancelButton = document.getElementById(CancelButtonId);

	if(OpButton.value == "Edit")
	{
		OpButton.value = "Save";
		CancelButton.style.display = "";
	}
	else
	{
		OpButton.value = "Edit";
		CancelButton.style.display = "none";
	}	
}

function Cancel_Click(OpButtonId, CancelButtonId)
{
	var OpButton = document.getElementById(OpButtonId);
	var CancelButton = document.getElementById(CancelButtonId);
	
	OpButton.value = "Edit";
	CancelButton.style.display = "none";
}

//Alert Overload
function alert(Message) 
{
    var MainDiv = document.getElementById("divMsgOuterMain");
    MainDiv.style.minHeight = document.documentElement.scrollHeight + "px";
    document.getElementById("spanMessage").innerHTML = Message;          
    $("#divMsgOuterMain").fadeIn('fast', null);
    $("#divMsgOuter").fadeIn('fast', null);
}

function alertClose() 
{
    //document.getElementById("spanMessage").innerHTML = "";
    $("#divMsgOuterMain").fadeOut('fast', null);
    $("#divMsgOuter").fadeOut('fast', null);    
}

//Confirm Overload
function Confirm(Message, SecCall, Sender)
{
    if (SecCall == 0)
    {
        var MainDiv = document.getElementById("divMsgOuterMain");
        MainDiv.style.minHeight = document.documentElement.scrollHeight + "px";
        document.getElementById("cmdOk").onclick = function () { Confirm(Message, 1, Sender); }
        document.getElementById("cmdCansel").onclick = function () { confirmClose(); }
        document.getElementById("spanMessageConfirm").innerHTML = Message;

        $("#divMsgOuterMain").fadeIn('fast', null);
        $("#divConfirm").fadeIn('fast', null);

        return false;
    }
    else if (SecCall == 1)
    {
        confirmClose();
        Sender.onclick = null;
        Sender.click();
        Sender.onclick = function () { Confirm(Message, 0, Sender); }
    }
}

window.confirm = function (Message, SecCall, Sender) { return Confirm(Message, SecCall, Sender) };

function confirmClose() {
    $("#divMsgOuterMain").fadeOut('fast', null);
    $("#divConfirm").fadeOut('fast', null);
    //document.getElementById("spanMessageConfirm").innerHTML = "";
}

//QueryString
function GetQuerystring(key, default_) 
{
    if (default_ == null) default_ = "";
    key = key.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
    var regex = new RegExp("[\\?&]" + key + "=([^&#]*)");
    var qs = regex.exec(window.location.href);
    if (qs == null)
        return default_;
    else
        return qs[1];
}

function LoadInputData() 
{
    var qs = GetQuerystring('mat', null);
    
    if (qs == 't')
    	document.getElementById("divViewTimber").style.display = "";
    else
    	document.getElementById("divViewConcrete").style.display = "";
    	
	document.getElementById("lblHeading").innerHTML = qs == 't' ? "Timber" : "Concrete";	
}

function PopulatePage1DefaultValues()
{
	var location = GetQuerystring('loaction', null);
	var scenario = GetQuerystring('scenario', null);
	var model = GetQuerystring('model', null);
	
	if(location != '' & scenario != '' & model != '')
	{
		var DrpLocation = document.getElementById('drpLocation');
		var DrpScenario = document.getElementById('drpScenario');
		var DrpModel = document.getElementById('drpModel');
		
		DrpLocation.options[location].selected = true;	
		DrpScenario.options[scenario].selected = true;	
		DrpModel.options[model].selected = true;	
	}
}

function PopulatePage2DefaultValues()
{
	var mat = GetQuerystring('mat', null);
	
	if(mat != '')
	{
		var selectedValue = 0;
		if (mat == 't'){
			selectedValue = 1;
			document.getElementById("divViewTimber").style.display = "";
		}
		else if(mat == 'c'){
			selectedValue = 2;
			document.getElementById("divViewConcrete").style.display = "";
		}
			
		var DrpMaterial = document.getElementById('drpMaterial');				
		DrpMaterial.options[selectedValue].selected = true;	
		
	}
}
