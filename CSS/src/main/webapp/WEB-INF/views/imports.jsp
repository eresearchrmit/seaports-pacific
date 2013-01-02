<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css" />"  media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/text.css" />"  media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/grid.css" />"  media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/layout.css" />"  media="screen" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/nav.css" />"  media="screen" />
<!--[if IE 6]><link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/ie6.css" />" media="screen" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/ie.css" />" media="screen" /><![endif]-->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/fancy-button/fancy-button.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/themes/base/jquery.ui.all.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/table/demo_page.css" />" />

<!-- BEGIN: load jquery -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.4.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/jquery.ui.core.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/jquery.ui.widget.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/jquery.ui.accordion.min.js" />"></script>
<!-- jQuery dialog related-->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/jquery.ui.widget.min.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/external/jquery.bgiframe-2.1.2.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/jquery.ui.mouse.min.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/jquery.ui.draggable.min.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/jquery.ui.position.min.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/jquery.ui.resizable.min.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/jquery.ui.dialog.min.js" />" ></script>
<!-- jQuery tables related-->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/jquery.ui.mouse.min.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/jquery.ui.sortable.min.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/table/jquery.dataTables.min.js" />" ></script>
<!-- jQuery effects -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/jquery.effects.core.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/jquery.effects.slide.min.js" />" ></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui/jquery.effects.fade.min.js" />" ></script>
<!-- jQuery MapHilight plugin -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.maphilight.js" />"></script>
<!-- END: load jquery -->




<!-- BEGIN: load jqplot -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.jqplot.min.css" />" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="<c:url value="/resources/js/jqPlot/excanvas.min.js" />"></script><![endif]-->
<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/jqPlot/jquery.jqplot.min.js" />"></script>
<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/jqPlot/plugins/jqplot.canvasTextRenderer.min.js" />"></script>
<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/jqPlot/plugins/jqplot.canvasAxisLabelRenderer.min.js" />"></script>
<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/jqPlot/plugins/jqplot.barRenderer.min.js" />"></script>
<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/jqPlot/plugins/jqplot.pieRenderer.min.js" />"></script>
<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/jqPlot/plugins/jqplot.categoryAxisRenderer.min.js" />"></script>
<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/jqPlot/plugins/jqplot.highlighter.min.js" />"></script>
<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/jqPlot/plugins/jqplot.pointLabels.min.js" />"></script>
<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/jqPlot/plugins/jqplot.donutRenderer.min.js" />"></script>
<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/jqPlot/plugins/jqplot.bubbleRenderer.min.js" />"></script>
<!-- END: load jqplot -->

<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/setup.js" />"></script>

<script type="text/javascript">
   $(document).ready(function () {
      setupLeftMenu();
      setSidebarHeight();
      // Uncomment to add sorting, pagination & search on tables
      //$('.datatable').dataTable();
   });
</script>
