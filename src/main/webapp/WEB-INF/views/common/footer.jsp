<script>
    $(document).ready(function () {
        // show the alert
        setTimeout(function () {
            $(".alert").alert('close');
        }, 2000);
    });
</script>

<script>
    $(document).ready(function(){

        $('#search_data').autocomplete({
            source: "/user/search",
            minLength: 1,
            select: function(event, ui)
            {
                $('#search_data').val(ui.item.value);
            }
        }).data('ui-autocomplete')._renderItem = function(ul, item){
            return $("<li class='ui-autocomplete-row'></li>")
                .data("item.autocomplete", item)
                .append(item.label)
                .appendTo(ul);
        };

    });
</script>


</body>
</html>