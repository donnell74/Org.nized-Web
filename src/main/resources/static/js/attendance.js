$(document).ready(function () {
    $("#date-dropdown").change(function () {
        $("#attendee-list").html("");
        $(".the-numbers").html("");
        $("#class-bonus-div").html("");
        if ($("#date-dropdown option:selected").text() != "Pick a date") {
            var dateurl = "checkin/checkins-by-date?date=" + $("#date-dropdown option:selected").text();
            var numsurl = "checkin/attend-by-date?date=" + $("#date-dropdown option:selected").text();
            var classurl = "checkin/person-by-classBonus?date=" + $("#date-dropdown option:selected").text();
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

            $.getJSON(numsurl, function (data) {
                var attendees = {};
                attendees.theTotal = data.total;
                attendees.member = data.member;
                attendees.general = data.general;
                $("#tot-attend").html(attendees.theTotal);
                $("#gen-attend").html(attendees.general);
                $("#mem-attend").html(attendees.member);
            });

            $.getJSON(classurl, function (data) {
                var classStuff = [];
                $.each(data, function (key, val) {
                    classStuff.push(key + "<ul>");
                    var personArray = val;
                    var arrayLength = personArray.length;
                    for (var i = 0; i < arrayLength; i++) {
                        classStuff.push("<li>" + personArray[i].first_name + " " + personArray[i].last_name + "</li>");
                    }
                    classStuff.push("</ul>");
                });
                $("<p/>", {
                    "class": "class-bonus-list",
                    html: classStuff.join("")
                }).appendTo("#class-bonus-div");
            });
        }
    });
});