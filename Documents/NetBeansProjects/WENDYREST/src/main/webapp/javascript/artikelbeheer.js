

// The root URL for the RESTful services ; relative url
var rootURL = "http://localhost:8080/WENDYREST/web";
var artikel;

// Retrieve voorstelling list when application starts 
findAll();

// Nothing to delete in initial application state
$('#btnDelete').hide();

// Register listeners
$('#btnSearch').click(function () {
    zoek($('#searchKey').val());
    return false;
});

// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function (e) {
    if (e.which === 13) {
        zoek($('#searchKey').val());
        e.preventDefault();
        return false;
    }
});

$('#btnAdd').click(function () {
    newVoorstelling();
    return false;
});

$('#btnSave').click(function () {
    if ($('#artikelId').val() !== '')
        updateVoorstelling();
    else
        addVoorstelling();
    return false;
});

$('#btnDelete').click(function () {
    deleteVoorstelling();
    return false;
});

$('#voorstellingList a').live('click', function () {
    find($(this).data('identity'));
});

//// Replace broken images with generic wine bottle
//$("img").error(function(){
//  $(this).attr("src", "pics/generic.jpg");
//
//});

function zoek(searchKey) {
    if (searchKey === '')
        findAll();
    else if (searchKey instanceof Object)
        findByName(searchKey);
    else
        find(searchKey);
}

function newVoorstelling() {
    $('#btnDelete').hide();
    artikel = {};
    renderDetails(artikel); // Display empty form
}

function findAll() {
    $.ajax({
        method: "GET",
        url: rootURL + "/artikelbeheer",
        dataType: 'json', // data type of response       
//        beforeSend: function() {
//            $content.append ('<div id="load"> LOADING </div>');        
//        }, 
//        complete: function(){$('#load').remove();},       
        success: renderList
    });
}

function renderList(data) {
    // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
    var list = data === null ? [] : (data instanceof Array ? data : [data]);

    $('#voorstellingList li').remove();
    $.each(list, function (index, artikel) {
        $('#voorstellingList').append('<li><a href="#" data-identity="'
                + artikel.artikelId + '">' + artikel.artikelnaam + '</a></li>');
    });
}

function findByName(searchKey) {
    $.ajax({
        method: "GET",
        url: rootURL + '/artikelbeheer/findByName/' + searchKey,
        dataType: "json",
        success: renderList
    });
}

function find(id) {
    $.ajax({
        method: "GET",
        url: rootURL + '/artikelbeheer/' + id,
        dataType: "json",
        success: function (data) {
            $('#btnDelete').show();            
            artikel = data;
            renderDetails(artikel);
        }
    });
}

function addVoorstelling() {
    //console.log('addVoorstelling');
    $.ajax({
        method: "POST",
        contentType: 'application/json',
        url: rootURL + "/artikelbeheer",
        dataType: "json",
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            alert('Voorstelling succesvol toegevoegd');
            $('#btnDelete').show();
            $('#artikelId').val(data.id);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('voorstelling toevoegen error: ' + textStatus + ": " +  errorThrown 
                    + ": " +  jqXHR);
        }
    });
}

function updateVoorstelling() {
    //console.log('updateVoorstelling');
    $.ajax({
        method: "PUT",
        contentType: 'application/json',
        url: rootURL + '/artikelbeheer/update/' + $('#artikelId').val(),
        dataType: "json",
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            alert('Voorstelling succesvol gewijzigd');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('voorstelling wijzigen error: ' + textStatus + ": " +  errorThrown 
                    + ": " +  jqXHR);
        }
    });
}

function deleteVoorstelling() {
    //console.log('deleteVoorstelling');
    $.ajax({
        method: "DELETE",
        url: rootURL + '/artikelbeheer/delete/' + $('#artikelId').val(),
        success: function (data, textStatus, jqXHR) {
            alert('Voorstelling succesvol verwijderd');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('voorstelling verwijderen error: ' + textStatus + ": " +  errorThrown 
                    + ": " +  jqXHR);
        }
    });
}

function renderDetails(artikel) {
    $('#artikelId').val(artikel.artikelId);
    $('#artikelnaam').val(artikel.artikelnaam);
    $('#artikelPrijs').val(artikel.artikelPrijs);
    $('#toegevoegdDoor').val(artikel.createdBy);
    $('#omschrijving').val(artikel.omschrijving);
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
    var artikelId = $('#artikelId').val();
    return JSON.stringify({
        "artikelId": artikelId === "" ? null : artikelId,
        "artikelnaam": $('#artikelnaam').val(),
        "artikelPrijs": $('#artikelPrijs').val(),
        "createdBy": $('#toegevoegdDoor').val(),
        "omschrijving": $('#omschrijving').val()
    });
}
// data: JSON.stringify($('#idthing').serializeObject())
