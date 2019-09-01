function goToPage(url, searchForm) {
  form = document.forms[searchForm];
  form.action = url;
  form.submit();
}

function del(link, searchForm) {
  $.ajax({
      url: link,
      type: 'DELETE',
      success: function(result) {
        document.forms[formName].submit();
      }
  });
}

function update(link, searchForm) {
  data = {}
  $.ajax({
        url: link,
        type: 'POST',
        data: data,
        success: function(result) {
          document.forms[searchForm].submit();
        }
    });
}

function create(link, searchForm) {
  data = {}
  $.ajax({
        url: link,
        type: 'PUT',
        data: data,
        success: function(result) {
          document.forms[searchForm].submit();
        }
    });
}