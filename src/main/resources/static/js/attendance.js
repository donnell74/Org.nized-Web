$(document).ready(function () {
    $("#date-dropdown").change(function () {
        if ($("#date-dropdown option:selected").text() != "Pick a date") {
            var dateurl = "checkin/checkins-by-date?date=" + $("#date-dropdown option:selected").text();
            $.getJSON(dateurl, function (data) {
                var items = [];
                $.each(data, function (key, val) {
                    items.push(val.email.first_name + " " + val.email.last_name + "<br />");
                });

                $("<p/>", {
                    "class": "my-new-list",
                    html: items.join("")
                }).appendTo("#attendee-list");
            });
        }
    });
});