window.onload = function() {
  registerOnSubmit();
}

function registerOnSubmit() {
  var form = document.getElementById("hello-form");
  form.onsubmit = submitHandler;
}

function submitHandler() {
    callWebservice(readName(), function(message) {
        writeMessage(message);
    },
    function(error) {
        console.log(error);
    });
    return false;
}

function readName() {
    return escapeXml(document.getElementById("hello-name-input").value);
}

function writeMessage(message) {
    document.getElementById("hello-message-input").value = message;
}

function callWebservice(name, onSuccess, onError) {
  var url = "services/hello";
  var request = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
     <soapenv:Header/>
     <soapenv:Body>
        <SayHello xmlns="http://learnwebservices.com/services/hello">
           <HelloRequest>
              <Name>${name}</Name>
           </HelloRequest>
        </SayHello>
     </soapenv:Body>
  </soapenv:Envelope>`;

  var fetchData = {
     method: 'POST',
     body: request
  };

  fetch(url, fetchData)
    .then(function(response) {
      if (response.status != 200) {
        throw new Error(`Status: ${response.status}`);
      }
      return response.text();
    })
    .then(function(xml) {
        var xmlDoc = new DOMParser().parseFromString(xml, "text/xml");
        var message = xmlDoc.getElementsByTagNameNS("http://learnwebservices.com/services/hello", "Message")[0].textContent;
        onSuccess(message);
    })
    .catch(function(error) {
      onError(error);
    });
}

function escapeXml(unsafe) {
  return unsafe.replace(/[<>&'"]/g, function (c) {
      switch (c) {
          case '<': return '&lt;';
          case '>': return '&gt;';
          case '&': return '&amp;';
          case '\'': return '&apos;';
          case '"': return '&quot;';
      }
  });
}