
            
                var folderToggle = $(".tree li > table");   
                
                $(function () {
                folderToggle.click(function(e) {
                e.preventDefault();
                $(this).find("+ ul").toggle("slow");
                $(this).toggleClass("active");
                });
                });
                
            
        