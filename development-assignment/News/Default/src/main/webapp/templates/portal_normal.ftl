<!DOCTYPE html>
<#include init />
<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">
<head>
   <link rel="icon" type="image/png" href="${images_folder}/favicon.ico">
   <title>Tara Customer-App</title>
   <meta content="initial-scale=1.0, width=device-width" name="viewport" />
   <@liferay_util["include"] page=top_head_include />

   <!-- Latest compiled and minified CSS -->
   	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
   <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

   	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

   	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.0/font/bootstrap-icons.css">
   	<!-- jQuery library -->
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    	<!-- Popper JS -->
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    	<!-- Latest compiled JavaScript -->
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		
		<!-- Font -->
		<link href="https://fonts.gstatic.com" rel="preconnect" />
		<link href="https://fonts.googleapis.com/css2?family=Varela+Round&amp;display=swap" rel="stylesheet" />
		
    	<!-- Mobile Responsive-->
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    
</head>
<body class="${css_class}">
<@liferay_ui["quick-access"] contentId="#main-content" />
<@liferay_util["include"] page=body_top_include />
<@liferay.control_menu />


   <section id="content">
      <h1 class="hide-accessible">${the_title}</h1>
      <#if selectable>
         <@liferay_util["include"] page=content_include />
      <#else>
         ${portletDisplay.recycle()}
         ${portletDisplay.setTitle(the_title)}
         <@liferay_theme["wrap-portlet"] page="portlet.ftl">
            <@liferay_util["include"] page=content_include />
         </@>
      </#if>
   </section>

</div>
<@liferay_util["include"] page=body_bottom_include />
<@liferay_util["include"] page=bottom_include />
</body>
</html>